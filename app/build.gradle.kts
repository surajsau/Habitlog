import java.io.FileInputStream
import java.util.Properties

plugins {
    kotlin("kapt")
    id("habit.primitive.android.application")
    id("habit.primitive.android.kotlin")
    id("habit.primitive.compose")
}

val keystorePropertiesFile = project.parent?.file("keystore.properties")
val keystoreExits = keystorePropertiesFile?.exists() ?: false

android {
    namespace = "jp.suji.habit"
    compileSdk = 33

    defaultConfig {
        applicationId = "jp.suji.habit"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        create("dev") {
            storeFile = project.file("dev.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }

        if (keystoreExits) {
            val keystoreProperties = Properties()
            keystoreProperties.load(FileInputStream(keystorePropertiesFile))
            create("prod") {
                storeFile = keystoreProperties["storeFile"]?.let { file(it) }
                storePassword = keystoreProperties["storePassword"] as? String
                keyAlias = keystoreProperties["keyAlias"] as? String
                keyPassword = keystoreProperties["keyPassword"] as? String
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = if (keystoreExits) signingConfigs.getByName("prod") else signingConfigs.getByName("dev")
        }

        debug {
            isDebuggable = true
            signingConfig = signingConfigs.getByName("dev")
        }
    }

    lint {
        warningsAsErrors = true
        abortOnError = false
    }
}

dependencies {
    implementation(projects.feature.habit)
    implementation(projects.feature.settings)
    implementation(projects.feature.add)
    implementation(projects.core.ui)
    implementation(projects.core.data)
    implementation(projects.core.di)

    implementation(libs.room.runtime)
}