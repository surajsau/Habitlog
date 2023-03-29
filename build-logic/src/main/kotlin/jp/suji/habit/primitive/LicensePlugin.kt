package jp.suji.habit.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

class LicensePlugin: Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.mikepenz.aboutlibraries.plugin")
            }

            dependencies {
                implementation(libs.findLibrary("license-tools-core"))
                implementation(libs.findLibrary("license-tools-ui"))
            }

            configureLicense()
        }
    }
}

private fun Project.configureLicense() {

}