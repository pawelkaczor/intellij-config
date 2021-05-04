package pl.newicom.sbt

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class IntellijConfigPluginTest extends AnyWordSpec with Matchers {
  "The plugin" should {
    "not fail" in {
      IntellijConfigPlugin.applyModifications("nonExistingDir") should have size (0)
    }
  }
}
