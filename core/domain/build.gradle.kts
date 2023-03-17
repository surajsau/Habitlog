plugins {
    id("habit.convention.feature")
}

android.namespace = "jp.suji.habit.domain"

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.model)
}