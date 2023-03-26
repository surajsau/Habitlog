plugins {
    id("habit.convention.feature")
    id("habit.primitive.showkase")
}

android.namespace = "jp.suji.habit.ui.settings"

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.feature.notification)
    implementation(projects.fake)

    implementation(libs.accompanist.permission)
}