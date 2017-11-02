name := "play-execution-context-issue"

version := "1.0"

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.12.4"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean, SbtWeb, LauncherJarPlugin)

libraryDependencies ++= Seq(
  guice,
  javaJdbc,
  javaWs
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )
