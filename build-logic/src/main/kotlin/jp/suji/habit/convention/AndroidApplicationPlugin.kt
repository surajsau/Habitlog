package jp.suji.habit.convention

import jp.suji.habit.primitive.application
import jp.suji.habit.primitive.setupAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("habit.primitive.android.kotlin")
                apply("habit.primitive.compose")
            }

            application {
                setupAndroid(isApplication = true)
            }
        }
    }
}