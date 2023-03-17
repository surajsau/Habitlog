pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "Habit"
include(":app")
include(":feature:habit")
include(":core:domain")
include(":core:data")
include(":core:model")
include(":feature:settings")
include(":core:ui")
include(":feature:add")
include(":core:exception")
include(":core:di")
include(":fake")
include(":feature:notification")
