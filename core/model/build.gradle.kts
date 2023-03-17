plugins {
    id("habit.convention.feature")
}

android.namespace = "jp.suji.habit.model"

dependencies {
    implementation(projects.core.data)
}