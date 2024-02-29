ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

val circeVersion = "0.14.1"

val cicre =  Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)

lazy val root = (project in file("."))
  .settings(
    name := "scalaEx",
    libraryDependencies ++= Seq(
      "org.apache.kafka" %% "kafka-streams-scala" % "3.6.1",
      "ch.qos.logback" % "logback-classic" % "1.5.1"
    ) ++ cicre
  )
