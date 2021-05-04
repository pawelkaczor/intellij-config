sbtPlugin := true

organization := "pl.newicom.sbt"
name := "intellij-config"

libraryDependencies ++= Seq(
  "com.github.geirolz" %% "advxml-core"              % "2.3.1",
  "org.scalatest"      %% "scalatest-wordspec"       % "3.2.7" % "test",
  "org.scalatest"      %% "scalatest-shouldmatchers" % "3.2.7" % "test"
)
