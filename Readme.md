## Intellij Config
[![Build Status](https://github.com/pawelkaczor/intellij-config/actions/workflows/scala.yml/badge.svg)](https://github.com/pawelkaczor/intellij-config/actions)
[![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/pl.newicom.ide.intellij/intellij-config?server=https%3A%2F%2Foss.sonatype.org)](https://mvnrepository.com/artifact/pl.newicom.ide.intellij/intellij-config)
[![GitHub license](https://img.shields.io/github/license/pawelkaczor/intellij-config)](https://github.com/pawelkaczor/intellij-config/blob/master/LICENSE)

SBT plugin that applies configurable modifications to the Intellij Idea project settings.

## Motivation 
Currently, Intellij Idea does not allow sharing project settings between projects [(IDEA-221422)](https://youtrack.jetbrains.com/issue/IDEA-221422). If you decide to change a setting, you need to do it manually in all your projects. 


## Solution (for Scala Sbt projects)

Intellij Idea keeps project settings in multiple xml files under PROJECT_ROOT/.idea directory.
Once the project is loaded by sbt, the plugin will modify the settings in these files.
Which modifications should be applied is configurable via environment variables.
Currently, the following modifications are supported:

Modification | Environment variable | Configuration file
--------------------- | ------------- | --------------------
Project language level | INTELLIJ_LANG_LEVEL | misc.xml
Jdk name | INTELLIJ_JDK_NAME | misc.xml
Display options dialog on VCS update? | INTELLIJ_DISPLAY_OPTIONS_DIALOG_ON_VCS_UPDATE (flag) | workspace.xml
Add new files to VCS silently? | INTELLIJ_ADD_NEW_FILES_TO_VCS (flag) | workspace.xml

## Implementation

The modifications are expressed in [advxml](https://github.com/geirolz/advxml) DSL. 
