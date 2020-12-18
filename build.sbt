import sbt._
import sbt.Keys._

lazy val root = (project in file("."))
  .enablePlugins(CloudflowApplicationPlugin, CloudflowAkkaPlugin)
  .settings(
    publish / skip := true,
    name := "cloudflow-test",
    libraryDependencies ++= Seq(
      "com.lightbend.akka" %% "akka-stream-alpakka-file"  % "1.1.2",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.2.1"
    )
  )