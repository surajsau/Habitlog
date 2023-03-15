package jp.suji.habit.convention

import jp.suji.habit.primitive.implementation
import jp.suji.habit.primitive.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

@Suppress("unused")
class AndroidFeaturePlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("habit.primitive.android.module")
                apply("habit.primitive.android.kotlin")
                apply("habit.primitive.compose")
                apply("habit.primitive.detekt")
            }

            dependencies {
                implementation(libs.findLibrary("accompanist-navigation-animation"))
            }
        }
    }
}