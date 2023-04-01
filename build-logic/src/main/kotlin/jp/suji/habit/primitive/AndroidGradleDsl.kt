package jp.suji.habit.primitive

import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.io.File

internal fun Project.application(action: BaseAppModuleExtension.() -> Unit) {
    extensions.configure(action)
}

internal fun Project.androidModule(action: LibraryExtension.() -> Unit) {
    extensions.configure(action)
}

internal fun Project.android(action: TestedExtension.() -> Unit) {
    extensions.configure(action)
}

internal fun Project.setupAndroid(isApplication: Boolean = false) {
    android {
        namespace?.let { this.namespace = it }
        compileSdkVersion(33)

        defaultConfig {
            if (isApplication) {
                applicationId = "jp.suji.habit"
            }

            minSdk = 26
            targetSdk = 33

            versionCode = 2
            versionName = "0.0.2"

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
            isCoreLibraryDesugaringEnabled = true
        }

        dependencies {
            add("coreLibraryDesugaring", libs.findLibrary("android-desugar").get())
        }

        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
    }
}

internal fun Project.buildComposeMetricsParameters(): List<String> {
    val metricParameters = mutableListOf<String>()
    val enableMetricsProvider = project.providers.gradleProperty("enableComposeCompilerMetrics")
    val enableMetrics = (enableMetricsProvider.orNull == "true")
    if (enableMetrics) {
        val metricsFolder = File(project.buildDir, "compose-metrics")
        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" + metricsFolder.absolutePath
        )

        metricParameters.add("-P")
        metricParameters.add(
            "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" + metricsFolder.absolutePath
        )
    }

    return metricParameters.toList()
}