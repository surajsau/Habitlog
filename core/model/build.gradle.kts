plugins {
    id("habit.convention.android.feature")
}

android.namespace = "jp.suji.habit.model"

dependencies {
    implementation(projects.core.data)
}