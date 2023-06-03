plugins {
    id(Plugins.androidApplication) version Versions.Config.gradle apply false
    id(Plugins.androidLibrary) version Versions.Config.gradle apply false

    kotlin(Plugins.android) version Versions.Config.kotlin apply false
    kotlin(Plugins.jvm) version Versions.Config.kotlin apply false
}

tasks.register("clean").configure {
    delete(rootProject.buildDir)
}