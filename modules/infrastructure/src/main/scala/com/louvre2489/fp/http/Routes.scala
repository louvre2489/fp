package com.louvre2489.fp.http

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{ ContentTypes, StatusCodes }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ Directive, ExceptionHandler, Route }
import akka.util.Timeout
import scalikejdbc.{ ConnectionPool, DB }

import scala.util.control.NonFatal
import com.louvre2489.fp.application.entity._
import com.louvre2489.fp.domain.value.SystemId
import javax.swing.text.html.HTML

/**
  * Routing Object
  */
object Routes {

  def apply()(implicit system: ActorSystem, timeout: Timeout): Routes =
    new Routes()
}

/**
  * Routing
  */
class Routes()(implicit system: ActorSystem, timeout: Timeout) extends SprayJsonSupport {

  /***
    * Custom Error Handler
    */
  implicit def customExceptionHandler: ExceptionHandler = ExceptionHandler {
    case NonFatal(ex) =>
      extractLog { implicit log =>
        extractUri { uri =>
          log.error(s"raised error!! uri: $uri, reason: ${ex.getMessage}")
          complete(StatusCodes.InternalServerError -> s"raised error!! uri: $uri, reason: ${ex.getMessage}")
        }
      }
  }

  /***
    * Routing
    */
  val routes: Route = handleExceptions(customExceptionHandler) {
    extractLog { implicit log =>
      extractUri { uri =>
        extractMethod { method =>
          log.info(s"call api. method: ${method.value}, uri: $uri")

          /**
            * コネクションの取得
            * @return
            */
          def borrow: DB = DB(ConnectionPool.borrow())

          /**
            * ローンパターンでアプリケーションを実行
            * @param db
            * @param application
            * @return
            */
          def using(db: DB)(application: DB => Route): Route = {
            try {
              application(db)
            } finally {
              db.close()
            }
          }

          path(FRONTEND_ROOT_PATH / Remaining) { resource =>
            pathEndOrSingleSlash {
              get {
                getFromResource(RESOURCE_ROOT_PATH + "/" + FRONTEND_ROOT_PATH + "/" + resource)
              }
            }

          } ~ path("login") {
            pathEndOrSingleSlash {
              get {
                getFromResource(HTML_FILE_PATH + "login.html")
              } ~
              post {
                // TODO ログイン処理
                log.error("POST!")
                getFromResource(HTML_FILE_PATH + "login.html")
              }
            }

          } ~ path("systems") {
            pathEndOrSingleSlash {
              get {

                using(borrow) { implicit db =>
                  implicit val systemListFormat = com.louvre2489.fp.http.marshaller.SystemJsonProtocol.systemListFormat

                  val data = new com.louvre2489.fp.application.query.QuerySystem(
                    com.louvre2489.fp.rdb.repository.SystemDao()
                  ).findAll.map(s => System(s.systemId, s.systemName))

                  complete(data)
                }
              }
            }
          } ~ path("subSystems") {
            pathEndOrSingleSlash {
              get {
                parameters('systemId) { systemId =>
                  // TODO バリデーション

                  using(borrow) { implicit db =>
                    implicit val subSystemListFormat =
                      com.louvre2489.fp.http.marshaller.SubSystemJsonProtocol.subSystemListFormat

                    val data = new com.louvre2489.fp.application.query.QuerySubSystem(
                      com.louvre2489.fp.rdb.repository.SubSystemDao()
                    ).findBySystemId(SystemId(systemId.toLong)).map(
                        s => SubSystem(s.subSystemId, s.subSystemName, s.systemId)
                      )

                    complete(data)
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}
