package net.abaro.opt.sbt.intellij

import advxml.core.transform.{XmlRule, XmlZoom}
import advxml.implicits._
import cats.implicits._

object MiscSettings {
  def fromEnvironment(env: Map[String, String]): MiscSettings =
    MiscSettings(env.get("INTELLIJ_LANG_LEVEL"), env.get("INTELLIJ_JDK_NAME"))
}

case class MiscSettings(languageLevel: Option[String], projectJdkName: Option[String]) extends IntellijConfigModification {

  def fileName: String = "misc.xml"

  private val projectComponentZoom: XmlZoom =
    $.component.filter(attrs(k"name" === "ProjectRootManager"))

  def modificationRules: List[XmlRule] = List(
    projectComponentZoom ==> (
      languageLevel.map(ll => SetAttr(_ => k"languageLevel" := ll)).getOrElse(NoAction)
        |+| projectJdkName.map(jdk => SetAttr(_ => k"project-jdk-name" := jdk)).getOrElse(NoAction)
    )
  )

}
