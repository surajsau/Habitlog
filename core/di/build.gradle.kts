plugins {
    id("habit.convention.feature")
}

android.namespace = "jp.suji.habit.di"

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.notification)

    implementation(libs.workmanager.runtime)
}