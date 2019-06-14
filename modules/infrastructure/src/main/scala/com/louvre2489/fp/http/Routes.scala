package com.louvre2489.fp.http

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ ExceptionHandler, Route }
import akka.util.Timeout
import scalikejdbc.{ ConnectionPool, DB }

import scala.util.control.NonFatal
import com.louvre2489.fp.application.entity._
import com.louvre2489.fp.application.usecase.LoginUser
import com.louvre2489.fp.domain.value.SystemId
import com.louvre2489.fp.rdb.repository.UserDao
import com.typesafe.config.{ Config, ConfigFactory }
import spray.json.RootJsonFormat

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

  private val conf: Config = ConfigFactory.load

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

  implicit val userCertificationFormat: RootJsonFormat[UserCertification] =
    com.louvre2489.fp.http.marshaller.UserCertificationJsonProtocol.userCertificationFormat

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
            * @param db DB接続情報
            * @param application アプリケーション処理
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
                entity(as[UserCertification]) { user =>
                  using(borrow) { implicit db =>
                    implicit val userDao: UserDao = new UserDao()

                    val loginUser = new LoginUser

                    val loginUserInfo = loginUser.login(user)

                    loginUserInfo.userCert match {
                      case success if success.isLoginSuccess => {
                        complete(success)
                      }
                      case fail => {
                        log.error(s"login error:${user.userId}")
                        complete(fail)
                      }
                    }
                  }
                }
              }
            }
          } ~ path("menu") {
            pathEndOrSingleSlash {
              get {
                using(borrow) { implicit db =>
                  getFromResource(HTML_FILE_PATH + "fp-common.html")
                }
              }
            }
          } ~ path("system") {
            pathEndOrSingleSlash {
              get {
                using(borrow) { implicit db =>
                  getFromResource(HTML_FILE_PATH + "system.html")
                }
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
