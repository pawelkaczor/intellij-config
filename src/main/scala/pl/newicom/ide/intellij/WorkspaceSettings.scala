package pl.newicom.ide.intellij

import advxml.core.transform.{XmlRule, XmlZoom}
import advxml.implicits._
import cats.implicits._

object WorkspaceSettings {
  def fromEnvironment(env: Map[String, String]): WorkspaceSettings =
    WorkspaceSettings(
      env.get("INTELLIJ_DISPLAY_OPTIONS_DIALOG_ON_VCS_UPDATE").exists(_.toBoolean),
      env.get("INTELLIJ_ADD_NEW_FILES_TO_VCS").exists(_.toBoolean)
    )
}

case class WorkspaceSettings(
    displayOptionsDialogOnVcsUpdate: Boolean,
    silentlyAddNewFilesToVcs: Boolean,
) extends IntellijConfigModification {

  def fileName: String = "workspace.xml"

  // TODO create vcsComponent if not present
  private val vcsComponentZoom: XmlZoom =
    $.component.filter(attrs(k"name" === "ProjectLevelVcsManager"))

  def modificationRules: List[XmlRule] = List(
    vcsComponentZoom ==> (
      (if (silentlyAddNewFilesToVcs) Replace(_ => <ConfirmationsSetting id="Add" value="2"/>) else NoAction)
        |+| Replace(_ => <OptionsSetting id="Update" value={displayOptionsDialogOnVcsUpdate.toString}/>)
    )
  )

}
