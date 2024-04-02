ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

val circeVersion = "0.14.1"
val http4sVersion = "0.23.26"

val cicre =  Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)


val http4s = Seq(
  "org.http4s" %% "http4s-ember-client" % http4sVersion,
  "org.http4s" %% "http4s-ember-server" % http4sVersion,
  "org.http4s" %% "http4s-dsl"          % http4sVersion,
)


lazy val root = (project in file("."))
  .settings(
    name := "scalaEx",
    libraryDependencies ++= Seq(
      "org.apache.kafka" %% "kafka-streams-scala" % "3.6.1",
      "ch.qos.logback" % "logback-classic" % "1.5.1",
      "io.javalin" % "javalin" % "6.1.3",
      "com.squareup.okhttp3" % "okhttp" % "4.12.0",
      "org.typelevel" %% "cats-effect" % "3.5.4"
    ) ++ cicre ++ http4s
  )
