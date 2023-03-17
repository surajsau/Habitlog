plugins {
    id("habit.convention.feature")
}

android.namespace = "jp.suji.habit.fake"

dependencies {
    implementation(projects.core.model)
}