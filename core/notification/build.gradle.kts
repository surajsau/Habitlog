plugins {
    id("habit.convention.feature")
}

android.namespace = "jp.suji.habit.notification"

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
}