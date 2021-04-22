name := "cron-utils-sisyphus"

organization := "com.cronutils"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.2"

crossScalaVersions := Seq("2.10.4", "2.11.2")

libraryDependencies ++= Seq(
  "com.cronutils" % "cron-utils" % "9.1.5",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.6",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.5" % "test"
)

initialCommands := "import com.cronutils._"
