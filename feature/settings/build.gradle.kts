plugins {
    id("habit.convention.feature")
    id("habit.primitive.showkase")
    id("habit.primitive.license")
}

android {
    namespace = "jp.suji.habit.ui.settings"

    buildTypes {
        release {
            buildConfigField("String", "VERSION", "\"${defaultConfig.versionName}\"")
        }

        debug {
            buildConfigField("String", "VERSION", "\"${defaultConfig.versionName}\"")
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.feature.notification)
    implementation(projects.fake)

    implementation(libs.accompanist.permission)
}