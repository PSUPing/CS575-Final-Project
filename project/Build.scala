import sbt._

object ApplicationBuild extends Build {

  val appName         = "modern-web-template"
  val appVersion      = "0.1-SNAPSHOT"

  val appDependencies = Seq(
    "com.google.inject" % "guice" % "3.0",
    "javax.inject" % "javax.inject" % "1",
    "org.mongodb" %% "casbah" % "2.7.4",

    "org.mockito" % "mockito-core" % "1.9.5" % "test"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
