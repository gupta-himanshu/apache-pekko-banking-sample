name := "credibility"

organization := "knoldus"

version := "0.2-SNAPSHOT"

scalaVersion := "2.13.6"

lazy val pekkoHttpVersion = "1.0.1"
lazy val pekkoVersion    = "1.0.3"
lazy val pekkoManagementVersion =  "1.0.0"
lazy val logbackVersion = "1.2.6"
lazy val scalaTestVersion = "3.2.10"

fork := true
ThisBuild / parallelExecution := false

scalacOptions ++= Seq(
  "-deprecation",
  "-Xfatal-warnings"
)

libraryDependencies ++= Seq(
  "org.apache.pekko" %% "pekko-http"            % pekkoHttpVersion,
  "org.apache.pekko" %% "pekko-http-spray-json" % pekkoHttpVersion,
  "org.apache.pekko" %% "pekko-cluster"         % pekkoVersion,
  "org.apache.pekko" %% "pekko-cluster-sharding"% pekkoVersion,
  "org.apache.pekko" %% "pekko-serialization-jackson" % pekkoVersion,
  "org.apache.pekko" %% "pekko-management" % pekkoManagementVersion,
  "org.apache.pekko" %% "pekko-management-cluster-http" % pekkoManagementVersion,
  "com.swissborg" %% "lithium" % "0.11.2",

  //Logback
  "org.apache.pekko" %% "pekko-slf4j" % pekkoVersion,
  "ch.qos.logback" % "logback-classic" % logbackVersion,

  //Test dependencies
  "org.apache.pekko" %% "pekko-http-testkit" % pekkoHttpVersion % Test,
  "org.apache.pekko" %% "pekko-testkit" % pekkoVersion % Test,
  "org.scalatest" %% "scalatest" % scalaTestVersion% Test
)
