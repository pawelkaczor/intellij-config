import sbt.Keys._
import sbt._

object Publish {
  lazy val settings = Seq(
    scmInfo := Some(ScmInfo(
      url("https://github.com/pawelkaczor/intellij-sbt-tasks"),
      "scm:git:git@github.com:pawelkaczor/intellij-sbt-tasks.git</"
    )),
    pomExtra :=
      <developers>
        <developer>
          <id>pawelkaczor</id>
          <name>Pawel Kaczor</name>
          <url>https://github.com/pawelkaczor</url>
        </developer>
      </developers>
  )
}
