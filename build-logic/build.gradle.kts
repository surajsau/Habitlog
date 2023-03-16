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
    implementation(libs.moko.resources.gradlePlugin)
    implementation(libs.sqldelight.gradlePlugin)
    implementation(libs.firebase.crahlytics.gradlePlugin)
    implementation(libs.konfig.gradlePlugin)
    implementation(libs.detekt.gradlePlugin)
    implementation(libs.paparazzi.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "habit.primitive.android.application"
            implementationClass = "jp.suji.habit.primitive.AndroidApplicationPlugin"
        }
        register("androidModule") {
            id = "habit.primitive.android.module"
            implementationClass = "jp.suji.habit.primitive.AndroidModulePlugin"
        }
        register("androidKotlin") {
            id = "habit.primitive.android.kotlin"
            implementationClass = "jp.suji.habit.primitive.AndroidKotlinPlugin"
        }
        register("firebase") {
            id = "habit.primitive.firebase"
            implementationClass = "jp.suji.habit.primitive.AndroidFirebasePlugin"
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
        register("androidFeature") {
            id = "habit.convention.android.feature"
            implementationClass = "jp.suji.habit.convention.AndroidFeaturePlugin"
        }
    }
}