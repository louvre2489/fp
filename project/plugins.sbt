resolvers ++= Seq(
//  "Sonatype OSS Snapshot Repository" at "https://oss.sonatype.org/content/gateway.repositories/snapshots/",
//  "Sonatype OSS Release Repository" at "https://oss.sonatype.org/content/gateway.repositories/releases/",
//  "Seasar Repository" at "http://maven.seasar.org/maven2/",
//  "Flyway" at "https://davidmweber.github.io/flyway-sbt.repo"
)

// scalafmt
addSbtPlugin("com.geirsson" % "sbt-scalafmt" % "1.5.1")

// ScalikeJDBC
// libraryDependencies += "com.h2database" % "h2" % "1.4.197"
// addSbtPlugin("org.scalikejdbc" %% "scalikejdbc-mapper-generator" % "3.3.2")
