import sbtrelease.ReleaseStateTransformations.{
  checkSnapshotDependencies,
  commitNextVersion,
  commitReleaseVersion,
  inquireVersions,
  pushChanges,
  runClean,
  runTest,
  setNextVersion,
  setReleaseVersion,
  tagRelease
}

sbtPlugin := true

organization := "pl.newicom.ide.intellij"
name := "intellij-config"

libraryDependencies ++= Seq(
  "com.github.geirolz" %% "advxml-core"              % "2.4.0",
  "org.scalatest"      %% "scalatest-wordspec"       % "3.2.7" % "test",
  "org.scalatest"      %% "scalatest-shouldmatchers" % "3.2.7" % "test"
)

homepage := Some(new URL("https://github.com/pawelkaczor/intellij-config"))
licenses := ("Apache2", new URL("https://raw.githubusercontent.com/pawelkaczor/intellij-config/master/LICENSE")) :: Nil

publishMavenStyle := true
publishTo := sonatypePublishToBundle.value

sonatypeProfileName := "pl.newicom"

versionScheme := Some("early-semver")
scalacOptions ++= Seq("-deprecation", "-feature")

Publish.settings

releaseProcess := Seq[ReleaseStep](
  checkSnapshotDependencies,
  inquireVersions,
  runClean,
  runTest,
  setReleaseVersion,
  commitReleaseVersion,
  tagRelease,
  releaseStepCommand("publishSigned"),
  releaseStepCommand("sonatypeBundleRelease"),
  setNextVersion,
  commitNextVersion,
  pushChanges
)
