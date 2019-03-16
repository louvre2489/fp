package com.louvre2489.fp.infrastructure.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.io.StdIn

object WebServer {

  def main(args: Array[String]) {

    implicit val system: ActorSystem             = ActorSystem(ACTOR_SYSTEM_NAME)
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContext = system.dispatcher
    implicit val timeout: Timeout                   = Timeout(5.seconds)

    // Configuration of Server
    val host = system.settings.config.getString(MY_HOST)
    val port = system.settings.config.getInt(MY_PORT)

    val bindingFuture = Http().bindAndHandle(Routes().routes, host, port)

    // message for console
    println(s"Server online at http://$host/$port/\nPress RETURN to stop...")

    StdIn.readLine() // let it run until user presses return

    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}
