plugins {
    id("habit.convention.feature")
    id("habit.primitive.showkase")
}

android.namespace = "jp.suji.habit.ui.core"

dependencies {
    implementation(libs.androidx.splashscreen)
}