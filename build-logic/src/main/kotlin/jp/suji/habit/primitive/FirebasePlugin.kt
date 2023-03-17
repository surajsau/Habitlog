package jp.suji.habit.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class FirebasePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.gms.google-services")
                apply("com.google.firebase.crashlytics")
            }

            dependencies {
                implementation(libs.findLibrary("firebase-analytics"))
                implementation(libs.findLibrary("firebase-crashlytics"))
            }
        }
    }
}