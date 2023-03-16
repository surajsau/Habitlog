plugins {
    id("habit.convention.android.feature")
}

android.namespace = "jp.suji.habit.fake"

dependencies {
    implementation(projects.core.model)
}