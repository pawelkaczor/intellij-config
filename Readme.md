## Intellij Config
[![Build Status](https://github.com/pawelkaczor/intellij-config/actions/workflows/scala.yml/badge.svg)](https://github.com/pawelkaczor/intellij-config/actions)
[![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/pl.newicom.sbt/intellij-config?server=https%3A%2F%2Foss.sonatype.org)](https://mvnrepository.com/artifact/pl.newicom.sbt/intellij-config)
[![GitHub license](https://img.shields.io/github/license/pawelkaczor/intellij-config)](https://github.com/pawelkaczor/intellij-config/blob/master/LICENSE)

A sbt plugin that applies configurable modifications to the Intellij Idea project settings.

Intellij Idea keeps project settings in multiple xml files under <project root>/.idea directory.
To perform modifications in these files, the plugin uses [Advxml](https://github.com/geirolz/advxml) library. 
The modifications are expressed in Advxml DSL. 
Which modifications should be applied is configrable via environament variables.
Currently, the following modifications are supported:

Modification | Environment variable | Configuration file
--------------------- | ------------- | --------------------
Project language level | INTELLIJ_LANG_LEVEL | misc.xml
Jdk name | INTELLIJ_JDK_NAME | misc.xml
Display options dialog on VCS update? | INTELLIJ_DISPLAY_OPTIONS_DIALOG_ON_VCS_UPDATE (flag) | workspace.xml
Add new files to VCS silently? | INTELLIJ_ADD_NEW_FILES_TO_VCS (flag) | workspace.xml