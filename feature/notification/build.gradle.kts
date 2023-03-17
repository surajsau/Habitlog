plugins {
    id("habit.convention.feature")
    id("habit.primitive.workmanager")
}

android.namespace = "jp.suji.habit.notification"

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.di)
    implementation(projects.core.ui)
}