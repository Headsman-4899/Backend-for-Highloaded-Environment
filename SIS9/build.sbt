import com.typesafe.sbt.packager.docker.ExecCmd
enablePlugins(JavaAppPackaging, AshScriptPlugin)

dockerBaseImage := "openjdk:8-jre-alpine"
packageName in Docker := "akkahttp-quickstart"

name := "SIS9"

version := "0.1"

scalaVersion := "2.13.3"

val AkkaVersion = "2.6.10"
val AkkaHttpVersion = "10.2.1"
val circeVersion = "0.13.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,

  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,

  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "de.heikoseeberger" %% "akka-http-circe" % "1.31.0"

)
