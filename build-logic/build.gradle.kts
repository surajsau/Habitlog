plugins {
    `kotlin-dsl`
}

group = "jp.suji.habit.buildlogic"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.android.gradlePlugin)
    implementation(libs.play.services.gradlePlugin)
    implementation(libs.firebase.crahlytics.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.paparazzi.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    implementation(libs.license.tools.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidModule") {
            id = "habit.primitive.android.module"
            implementationClass = "jp.suji.habit.primitive.AndroidModulePlugin"
        }
        register("androidKotlin") {
            id = "habit.primitive.android.kotlin"
            implementationClass = "jp.suji.habit.primitive.AndroidKotlinPlugin"
        }
        register("compose") {
            id = "habit.primitive.compose"
            implementationClass = "jp.suji.habit.primitive.AndroidComposePlugin"
        }
        register("compose.icons") {
            id = "habit.primitive.compose.icons"
            implementationClass = "jp.suji.habit.primitive.AndroidIconsPlugin"
        }
        register("coroutine") {
            id = "habit.primitive.coroutine"
            implementationClass = "jp.suji.habit.primitive.CoroutinePlugin"
        }
        register("detekt") {
            id = "habit.primitive.detekt"
            implementationClass = "jp.suji.habit.primitive.DetektPlugin"
        }
        register("paparazzi") {
            id = "habit.primitive.paparazzi"
            implementationClass = "jp.suji.habit.primitive.PaparazziPlugin"
        }
        register("showkase") {
            id = "habit.primitive.showkase"
            implementationClass = "jp.suji.habit.primitive.ShowkasePlugin"
        }
        register("firebase") {
            id = "habit.primitive.firebase"
            implementationClass = "jp.suji.habit.primitive.FirebasePlugin"
        }
        register("workmanager") {
            id = "habit.primitive.workmanager"
            implementationClass = "jp.suji.habit.primitive.WorkManagerPlugin"
        }
        register("license") {
            id = "habit.primitive.license"
            implementationClass = "jp.suji.habit.primitive.LicensePlugin"
        }
        register("androidFeature") {
            id = "habit.convention.feature"
            implementationClass = "jp.suji.habit.convention.AndroidFeaturePlugin"
        }
        register("application") {
            id = "habit.convention.application"
            implementationClass = "jp.suji.habit.convention.AndroidApplicationPlugin"
        }
    }
}