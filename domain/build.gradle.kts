plugins {
    id(Plugins.javaLibrary)
    kotlin(Plugins.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // Network
    api(Dependency.Network.rxJava)
    // Test
    api(Dependency.Test.mockk)
    api(Dependency.Test.junit)
}