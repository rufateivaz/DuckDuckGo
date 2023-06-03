plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
}

android {
    namespace = "com.sample.viewer"
    compileSdk = Versions.Config.compileSdk

    defaultConfig {
        minSdk = Versions.Config.minSdk
        targetSdk = Versions.Config.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/*"
        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    flavorDimensions += "characters"
    productFlavors {
        create("simpsonsviewer") {
            applicationId = "com.sample.simpsonsviewer"
            resValue("string", "app_name", "SimpsonsViewer")

        }
        create("wireviewer") {
            applicationId = "com.sample.wireviewer"
            resValue("string", "app_name", "WireViewer")
        }
    }
}

dependencies {
    // Data Module Dependency
    implementation(project(":data"))

    // UI
    implementation(Dependency.UI.appcompat)
    implementation(Dependency.UI.material)
    implementation(Dependency.UI.recyclerView)
    implementation(Dependency.UI.cardView)
    implementation(Dependency.UI.glide)
    implementation(Dependency.UI.slidingPaneLayout)

    // Network
    implementation(Dependency.Network.rxAndroid)
    implementation(Dependency.Network.rxKotlin)

    // Test
    androidTestImplementation(Dependency.Test.junitExt)
    androidTestImplementation(Dependency.Test.espresso)
    testImplementation(Dependency.Test.arch)
}