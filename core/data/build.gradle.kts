plugins {
    id("habit.convention.feature")
    kotlin("kapt")
}

android.namespace = "jp.suji.habit.data"

dependencies {
    implementation(projects.core.exception)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    testImplementation(libs.room.test)
    testImplementation(libs.junit)
    testImplementation(libs.coroutines.test)

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation("app.cash.turbine:turbine:0.12.1")
    androidTestImplementation(libs.coroutines.test)

}