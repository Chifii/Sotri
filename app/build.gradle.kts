plugins {
    id("com.android.application")
    id("kotlin-android")
    id("io.gitlab.arturbosch.detekt")
}


android {
    compileSdk = (rootProject.extra["maxSdk"].toString().toInt())

    defaultConfig {
        applicationId = rootProject.extra["applicationId"] as String
        namespace = rootProject.extra["applicationId"] as String
        minSdk = (rootProject.extra["minSdk"].toString().toInt())
        targetSdk = (rootProject.extra["maxSdk"].toString().toInt())
        versionCode = rootProject.extra["versionCode"].toString().toInt()
        versionName = rootProject.extra["versionName"] as String

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

detekt {
    toolVersion = "1.23.1"
    source.setFrom("src/main/java", "src/main/kotlin")
    parallel = true
    config.setFrom("path/to/config.yml")
    buildUponDefaultConfig = false
    allRules = false
    baseline = file("path/to/baseline.xml")
    disableDefaultRuleSets = false
    debug = true
    ignoreFailures = false
    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("production")
    ignoredVariants = listOf("productionRelease")
    basePath = projectDir.absolutePath
}

dependencies {
    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlinVersion"]}")

    // AndroidX
    implementation("androidx.core:core-ktx:${rootProject.extra["androidxCoreVersion"]}")
    implementation("androidx.appcompat:appcompat:${rootProject.extra["androidxAppcompatVersion"]}")
    implementation("androidx.constraintlayout:constraintlayout:${rootProject.extra["constraintLayoutVersion"]}")

    // Material Design
    implementation("com.google.android.material:material:${rootProject.extra["materialVersion"]}")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:${rootProject.extra["firebaseBomVersion"]}"))
    implementation("com.google.firebase:firebase-analytics-ktx")

    // Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:${rootProject.extra["navigationVersion"]}")
    implementation("androidx.navigation:navigation-ui-ktx:${rootProject.extra["navigationVersion"]}")

    // Jetpack Compose
    implementation("androidx.compose.ui:ui:${rootProject.extra["composeVersion"]}")
    implementation("androidx.compose.material:material:${rootProject.extra["composeVersion"]}")

    // Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${rootProject.extra["coroutinesVersion"]}")

    // Testing
    testImplementation("junit:junit:${rootProject.extra["junitVersion"]}")
    androidTestImplementation("androidx.test.ext:junit:${rootProject.extra["androidxJUnitVersion"]}")
    androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.extra["espressoVersion"]}")
}

