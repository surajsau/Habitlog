plugins {
    id("habit.convention.feature")
    id("habit.primitive.paparazzi")
    id("habit.primitive.showkase")
}

android.namespace = "jp.suji.habit.ui.add"

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.model)
    implementation(projects.core.ui)
    implementation(projects.core.di)
    implementation(projects.core.notification)
    implementation(projects.core.ext)

    implementation(libs.workmanager.runtime)
    implementation(libs.accompanist.permission)

    testImplementation(libs.test.parameter.injector)
}