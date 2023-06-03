plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kapt)
    kotlin(Plugins.android)
}

android {
    namespace = "com.sample.data"
    compileSdk = Versions.Config.compileSdk

    defaultConfig {
        minSdk = Versions.Config.minSdk
        targetSdk = Versions.Config.targetSdk

        buildConfigField("String", "API", "\"http://api.duckduckgo.com/\"")
        buildConfigField("String", "HOST", "\"http://duckduckgo.com/\"")

        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildFeatures.buildConfig = true

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
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
            //dimension = "characters"
            buildConfigField("String", "API_EXT", "\"simpsons\"")
        }
        create("wireviewer") {
            //dimension = "characters"
            buildConfigField("String", "API_EXT", "\"the wire\"")
        }
    }
}

dependencies {
    // Domain Module Dependency
    api(project(":domain"))

    // Data
    api(Dependency.Data.koin)
    implementation(Dependency.Data.room)
    implementation(Dependency.Data.roomRxJava)
    kapt(Dependency.Data.roomKapt)

    // Network
    implementation(Dependency.Network.retrofit)
    implementation(Dependency.Network.retrofitAdapterRxJava3)
    implementation(Dependency.Network.retrofitGsonConverter)
    implementation(Dependency.Network.gson)
    implementation(platform(Dependency.Network.okHttpBom))
    implementation(Dependency.Network.okHttp)
    implementation(Dependency.Network.okHttpIntercepter)
}