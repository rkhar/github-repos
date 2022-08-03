ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "github-repos"
  )

val akkaVersion = "2.6.19"
val akkaHttpVersion = "10.2.9"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.11",
  "org.scalameta" %% "scalafmt-dynamic" % "3.5.8"
)
