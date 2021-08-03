package pl.newicom.ide.intellij

import advxml.implicits._
import cats.instances.try_._
import sbt.Keys._
import sbt._

import scala.language.implicitConversions
import scala.sys.env
import scala.util.Try
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
      applyModifications()
      state
    }
  )

  private[ide] def applyModifications(configDir: String = ".idea"): Seq[Unit] =
    for {
      mod          <- modifications
      xmlConfig    <- Try(XML.loadFile(configFile(mod, configDir))).toOption.toList
      newXmlConfig <- xmlConfig.transform(mod.modificationRules).toOption.toList
    } yield {
      XML.save(configFile(mod, configDir).getPath, newXmlConfig.head)
    }

  def configFile(mod: IntellijConfigModification, intellijConfigDir: String): sbt.File =
    sbt.file(s"$intellijConfigDir/${mod.fileName}")

}
