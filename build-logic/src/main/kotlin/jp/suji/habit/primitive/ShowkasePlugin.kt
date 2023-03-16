package jp.suji.habit.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class ShowkasePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }
            dependencies {
                implementation(libs.findLibrary("showkase-runtime"))
                ksp(libs.findLibrary("showkase-processor"))
            }
        }
    }
}