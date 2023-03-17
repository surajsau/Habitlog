plugins {
    id("habit.convention.feature")
    id("habit.primitive.showkase")
    id("habit.primitive.paparazzi")
}

android.namespace = "jp.suji.habit.ui.habit"

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.ui)
    implementation(projects.core.di)
    implementation(projects.fake)

    testImplementation(libs.test.parameter.injector)
    testImplementation(project(mapOf("path" to ":core:ui")))
}