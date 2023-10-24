plugins {
	id("com.android.application")
	id("kotlin-android")
	id("io.gitlab.arturbosch.detekt")
	id("com.google.gms.google-services")
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
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildFeatures {
		viewBinding = true
		compose = true
		buildConfig = true
	}

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
			)
		}
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}

	composeOptions {
		kotlinCompilerExtensionVersion = "1.4.3"
	}
	packaging {
		resources {
			excludes += "/META-INF/{AL2.0,LGPL2.1}"
		}
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
	implementation("com.google.firebase:firebase-auth-ktx")
	implementation("com.google.firebase:firebase-firestore-ktx")
	implementation("com.google.firebase:firebase-storage-ktx")
	implementation("com.google.firebase:firebase-database:${rootProject.extra["firebaseDatabase"]}")
	implementation("com.google.firebase:firebase-analytics-ktx")

	// Navigation
	implementation("androidx.navigation:navigation-fragment-ktx:${rootProject.extra["navigationVersion"]}")
	implementation("androidx.navigation:navigation-ui-ktx:${rootProject.extra["navigationVersion"]}")

	// Jetpack Compose
	implementation("androidx.compose.ui:ui:${rootProject.extra["composeVersion"]}")
	implementation("androidx.compose.material:material:${rootProject.extra["composeVersion"]}")
	implementation("androidx.compose.material3:material3")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation("com.google.firebase:firebase-auth-ktx:${rootProject.extra["firebaseAuth"]}")
	implementation("com.google.firebase:firebase-crashlytics-buildtools:${rootProject.extra["firebaseCrashlyticsBuildtools"]}")
	implementation("androidx.camera:camera-core:${rootProject.extra["androidxCameraCore"]}")
	implementation("androidx.camera:camera-lifecycle:${rootProject.extra["androidxCameraLifeCycle"]}")
	debugImplementation("androidx.compose.ui:ui-tooling")
	implementation("androidx.compose.material:material-icons-core")
	implementation("androidx.compose.material:material-icons-extended")
	implementation("androidx.compose.material3:material3-window-size-class")
	implementation("androidx.activity:activity-compose:${rootProject.extra["androidxActivityCompose"]}")
	implementation("androidx.lifecycle:lifecycle-viewmodel-compose:${rootProject.extra["androidxViewModelCompose"]}")
	implementation("androidx.compose.runtime:runtime-livedata")
	implementation("androidx.compose.runtime:runtime-rxjava2")


	// Kotlin Coroutines
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${rootProject.extra["coroutinesVersion"]}")
	implementation("androidx.annotation:annotation:${rootProject.extra["androidxAnnotation"]}")
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:${rootProject.extra["androidxLifecycle"]}")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${rootProject.extra["androidxLifecycle"]}")
	implementation("androidx.lifecycle:lifecycle-runtime-ktx:${rootProject.extra["androidxLifecycle"]}")
	implementation("androidx.activity:activity-compose:${rootProject.extra["androidxActivityCompose"]}")
	implementation(platform("androidx.compose:compose-bom:${rootProject.extra["composeBoomVersion"]}"))
	implementation("androidx.compose.ui:ui-graphics")
	implementation("androidx.compose.ui:ui-tooling-preview")
	implementation("androidx.compose.material3:material3")

	// Shimmer
	implementation("com.valentinilk.shimmer:compose-shimmer:${rootProject.extra["shimmerVersion"]}")


	// Testing
	testImplementation("junit:junit:${rootProject.extra["junitVersion"]}")
	androidTestImplementation("androidx.test.ext:junit:${rootProject.extra["androidxJUnitVersion"]}")
	androidTestImplementation("androidx.test.espresso:espresso-core:${rootProject.extra["espressoVersion"]}")
	androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
	androidTestImplementation("androidx.compose.ui:ui-test-junit4")
	debugImplementation("androidx.compose.ui:ui-tooling")
	debugImplementation("androidx.compose.ui:ui-test-manifest")

	// implementation("androidx.camera:camera-camera2:1.1.0")
	implementation("io.coil-kt:coil-compose:${rootProject.extra["coilVersion"]}")

}

