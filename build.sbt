import Settings._

val baseName = "fp"

lazy val domain = (project in file("modules/domain"))
  .settings(
    name := s"$baseName-domain"
  )
  .settings(coreSettings)

lazy val interActor = (project in file("modules/interactor"))
  .settings(
    name := s"$baseName-interactor"
  )
  .settings(coreSettings)
  .dependsOn(domain)

lazy val application = (project in file("modules/application"))
  .settings(
    name := s"$baseName-application"
  )
  .settings(coreSettings)
  .dependsOn(domain, interActor)

lazy val http = (project in file("modules/infrastructure/http"))
  .settings(
    name := s"$baseName-http",
    mainClass in Compile := Some(
      "com.louvre2489.fp.infrastructure.http.WebServer"),
    libraryDependencies ++= Seq(
      Akka.akka_actor,
      Akka.akka_stream,
      Akka.akka_slf4j,
      Akka.akka_testkit % Test,
      AkkaHttp.akka_http,
      AkkaHttp.akka_http_spray_json,
      AkkaHttp.akka_http_testkit % Test,
      Spray.spray
    ),
  )
  .settings(coreSettings)
  .dependsOn(domain, interActor)

lazy val rdb = (project in file("modules/infrastructure/rdb"))
  .settings(
    name := s"$baseName-rdb",
    libraryDependencies ++= Seq(
      ScalikeJdbc.scalikeJdbc,
      H2.h2
    )
  )
  .settings(coreSettings)
  .dependsOn(domain, interActor)

lazy val `root` = (project in file("."))
  .settings(
    name := baseName
  )
  .settings(coreSettings)
  .aggregate(
    domain,
    application,
    interActor,
    http,
    rdb
  )
