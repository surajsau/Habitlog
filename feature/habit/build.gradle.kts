plugins {
    id("habit.convention.android.feature")
}

android.namespace = "jp.suji.habit.ui.habit"

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.ui)
    implementation(projects.core.di)
}