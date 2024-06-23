import sbtassembly.AssemblyKeys.assembly

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

lazy val root = (project in file("."))
  .settings(
    name := "ecr",
    testFrameworks += new TestFramework("weaver.framework.CatsEffect"),
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.5.4",
      "com.disneystreaming" %% "weaver-cats" % "0.8.3" % Test
    ) ,
    assembly / assemblyJarName := "ecr-assembly-0.1.0-SNAPSHOT.jar",
  )