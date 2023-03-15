package jp.suji.habit.primitive

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.JavaVersion

@Suppress("Unused")
class AndroidKotlinPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.android")
            }

            android {
                kotlinOptions {
                    allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

                    freeCompilerArgs = freeCompilerArgs + listOf(
                        "-opt-in=kotlin.RequiresOptIn",
                        "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                        "-Xcontext-receivers"
                    )

                    jvmTarget = "17"
                }
            }
        }
    }

}