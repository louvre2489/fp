package com.louvre2489.fp.http

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import com.louvre2489.fp.rdb.db.DBSettings
import com.typesafe.config.ConfigFactory
import scalikejdbc.{ ConnectionPool, DB, SQL }

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.io.StdIn
import scalikejdbc.config._

object WebServer {

  def main(args: Array[String]) {

    val conf = ConfigFactory.load

    // DB セットアップ
    //DBs.setup(DBSettings.poolName)
    DBs.setup()

    // テストクエリ
    DB readOnly { implicit session =>
      val one = SQL("SELECT 1 AS one").map(rs => rs.int("one")).single.apply()
      println(one)
    }

    implicit val system: ActorSystem             = ActorSystem(conf.getString(ACTOR_SYSTEM_NAME))
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContext = system.dispatcher
    implicit val timeout: Timeout                   = Timeout(5.seconds)

    // Configuration of Server
    val host = conf.getString(MY_HOST)
    val port = conf.getInt(MY_PORT)

    val bindingFuture = Http().bindAndHandle(Routes().routes, host, port)

    // message for console
    println(s"Server online at http://$host/$port/\nPress RETURN to stop...")

    StdIn.readLine() // let it run until user presses return

    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => {
        // DB接続終了
        DBs.close()
        // システム終了
        system.terminate()
      }) // and shutdown when done
  }
}
