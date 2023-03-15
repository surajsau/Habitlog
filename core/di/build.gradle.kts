plugins {
    id("habit.convention.android.feature")
}

android.namespace = "jp.suji.habit.di"

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
}