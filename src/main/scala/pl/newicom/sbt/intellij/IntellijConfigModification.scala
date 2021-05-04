package pl.newicom.sbt.intellij

import advxml.core.transform.XmlRule

trait IntellijConfigModification {
  def fileName: String
  def modificationRules: List[XmlRule]
}