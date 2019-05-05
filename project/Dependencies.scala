import sbt._

object Config {
  val version = "1.3.4"
  val config  = "com.typesafe" % "config" % version
}

object ScalaTest {
  val version        = "3.0.5"
  val core: ModuleID = "org.scalatest" %% "scalatest" % version
}

object Akka {
  val version      = "2.5.21"
  val akka_actor   = "com.typesafe.akka" %% "akka-actor" % version exclude ("com.typesafe", "config")
  val akka_slf4j   = "com.typesafe.akka" %% "akka-slf4j" % version
  val akka_stream  = "com.typesafe.akka" %% "akka-stream" % version
  val akka_testkit = "com.typesafe.akka" %% "akka-testkit" % version
}

object AkkaHttp {
  val version              = "10.1.7"
  val akka_http            = "com.typesafe.akka" %% "akka-http" % version
  val akka_http_spray_json = "com.typesafe.akka" %% "akka-http-spray-json" % version
  val akka_http_testkit    = "com.typesafe.akka" %% "akka-http-testkit" % version
}

object Spray {
  val version = "1.3.5"
  val spray   = "io.spray" %% "spray-json" % version
}

object ScalikeJdbc {
  val version           = "3.3.2"
  val scalikeJdbc       = "org.scalikejdbc" %% "scalikejdbc" % version
  val scalikeJdbcConfig = "org.scalikejdbc" %% "scalikejdbc-config" % version
  val scalikeJdbcTest   = "org.scalikejdbc" %% "scalikejdbc-test" % "3.3.2" % "test"
}

object H2 {
  val version = "1.4.197"
  val h2      = "com.h2database" % "h2" % version
}

object Logback {
  val version = "1.2.3"
  val logback = "ch.qos.logback" % "logback-classic" % version
}
