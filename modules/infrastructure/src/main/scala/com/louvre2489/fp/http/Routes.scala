package com.louvre2489.fp.http

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity, StatusCodes }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.{ ExceptionHandler, Route }
import akka.util.Timeout

import scala.util.control.NonFatal

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

          pathPrefix("test") {
            pathEndOrSingleSlash {
              get {
                complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
              }
            }
          }
        }
      }
    }
  }
}
