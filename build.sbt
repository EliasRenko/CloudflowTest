import sbt._
import sbt.Keys._

lazy val root = (project in file("."))

  .enablePlugins(CloudflowApplicationPlugin, CloudflowAkkaPlugin)
  .settings(
    scalaVersion := "2.12.10",
    publish / skip := true,
    name := "cloudflow-test",
    blueprint := Some("blueprint.conf"),
    libraryDependencies ++= Seq(
      "com.lightbend.akka"     %% "akka-stream-alpakka-file"  % "1.1.2",
      "com.typesafe.akka"      %% "akka-http-spray-json"      % "10.1.12",
      "ch.qos.logback"         %  "logback-classic"           % "1.2.3",
      "com.typesafe.akka"      %% "akka-http-testkit"         % "10.1.12" % "test",
      "org.scalatest"          %% "scalatest"                 % "3.0.8"  % "test"
    ),

    crossScalaVersions := Vector(scalaVersion.value),
    scalacOptions ++= Seq(
      "-encoding", "UTF-8",
      "-target:jvm-1.8",
      "-Xlog-reflective-calls",
      "-Xlint",
      "-Ywarn-unused",
      "-Ywarn-unused-import",
      "-deprecation",
      "-feature",
      "-language:_",
      "-unchecked"
    ),

    runLocalConfigFile := Some("resources/local.conf"),
  )

lazy val datamodel = (project in file("./my-cloudflow-library"))
  .enablePlugins(CloudflowLibraryPlugin)
  .settings(
    schemaCodeGenerator := SchemaCodeGenerator.Scala,
    schemaPaths := Map(SchemaFormat.Avro -> "src/main/avro")
  )