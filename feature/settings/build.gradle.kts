plugins {
    id("habit.convention.android.feature")
}

android.namespace = "jp.suji.habit.ui.settings"

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.ui)
}