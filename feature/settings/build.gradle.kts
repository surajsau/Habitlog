plugins {
    id("habit.convention.android.feature")
    id("habit.primitive.showkase")
}

android.namespace = "jp.suji.habit.ui.settings"

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.ui)
}