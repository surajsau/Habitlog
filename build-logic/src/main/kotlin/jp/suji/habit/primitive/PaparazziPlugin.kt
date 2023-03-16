package jp.suji.habit.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project

class PaparazziPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("app.cash.paparazzi")
        }
    }

}