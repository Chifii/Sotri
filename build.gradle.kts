buildscript {
    val kotlinVersion = rootProject.extra["kotlinVersion"] as String
    val androidGradlePluginVersion = rootProject.extra["androidGradlePluginVersion"] as String
    val googleServicesVersion = rootProject.extra["googleServicesVersion"] as String

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:$androidGradlePluginVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.google.gms:google-services:$googleServicesVersion")
        classpath("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.1")
    }
}
