plugins {
    id("habit.convention.feature")
    id("habit.primitive.workmanager")
}

android.namespace = "jp.suji.habit.feature.notification"

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.core.di)
}