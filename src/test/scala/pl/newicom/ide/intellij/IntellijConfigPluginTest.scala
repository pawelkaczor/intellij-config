package pl.newicom.ide.intellij

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class IntellijConfigPluginTest extends AnyWordSpec with Matchers {
  "The plugin" when {
    "the Intellij config dir is missing" should {
      "do nothing" in {
        IntellijConfigPlugin.applyModifications("nonExistingDir") should have size 0
      }
    }
  }
}
