package com.louvre2489.fp.http

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{ ContentTypes, StatusCodes }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ ExceptionHandler, Route }
import akka.util.Timeout
import scalikejdbc.{ ConnectionPool, DB }

import scala.util.control.NonFatal
import com.louvre2489.fp.application.entity._
import com.louvre2489.fp.domain.value.SystemId

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

          // DB セットアップ
          val conn            = ConnectionPool().borrow()
          implicit val db: DB = DB(conn)

          // frontendのルート
          val frontendRootPath = "frontend"
          val htmlFilePath     = frontendRootPath + "/src/html/"

          path(frontendRootPath / Remaining) { resource =>
            log.error(frontendRootPath + "/" + resource)
            getFromResource(frontendRootPath + "/" + resource)
          } ~
          path("login") {
            pathEndOrSingleSlash {
              get {
                getFromResource(htmlFilePath + "login.html")
              }
            }
          } ~ path("systems") {
            pathEndOrSingleSlash {
              get {

                implicit val systemListFormat = com.louvre2489.fp.http.marshaller.SystemJsonProtocol.systemListFormat

                val data = new com.louvre2489.fp.application.query.QuerySystem(
                  com.louvre2489.fp.rdb.repository.SystemDao()
                ).findAll.map(s => System(s.systemId, s.systemName))

                complete(data)
              }
            }
          } ~ path("subSystems") {
            pathEndOrSingleSlash {
              get {
                parameters('systemId) { systemId =>
                  // TODO バリデーション

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
