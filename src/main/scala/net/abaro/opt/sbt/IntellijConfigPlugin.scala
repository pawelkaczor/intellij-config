package net.abaro.opt.sbt

import advxml.implicits._
import cats.instances.try_._
import net.abaro.opt.sbt.intellij.{IntellijConfigModification, MiscSettings, WorkspaceSettings}
import sbt.Keys._
import sbt._

import scala.language.implicitConversions
import scala.sys.env
import scala.xml._

object IntellijConfigPlugin extends AutoPlugin {

  override def trigger  = allRequirements
  override def requires = plugins.JvmPlugin

  private def modifications: List[IntellijConfigModification] = {
    val environment = env
    List(
      MiscSettings.fromEnvironment(environment),
      WorkspaceSettings.fromEnvironment(environment)
    )
  }

  override val globalSettings: Seq[Def.Setting[_]] = Seq(
    onLoad in Global := (onLoad in Global).value andThen { state =>
      modifications.foreach(mod => {
        val confFile = file(s".idea/${mod.fileName}")
        val newConfXml = XML.loadFile(confFile)
          .transform(mod.modificationRules)
          .get.head

        XML.save(filename = confFile.getPath, node = newConfXml)
      })
      state
    }
  )

}
