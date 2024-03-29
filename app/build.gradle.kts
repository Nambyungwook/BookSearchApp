plugins {
    id(Plugins.ANDROID_APPLICATION)
    id(Plugins.KOTLIN_ANDROID)
    id(Plugins.KAPT)
    id(Plugins.SECRETS_GRADLE_PLUGIN)
    id(Plugins.SAFEARGS)
    id(Plugins.PARCELIZE)
    id(Plugins.HILT_PLUGIN)
}

android {
    compileSdk = DefaultConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = "com.nbw.booksearchapp"
        minSdk = DefaultConfig.MIN_SDK_VERSION
        targetSdk = DefaultConfig.TARGET_SDK_VERSION
        versionCode = DefaultConfig.VERSION_CODE
        versionName = DefaultConfig.VERSION_NAME

//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.nbw.booksearchapp.HiltTestRunner"
    }

    buildTypes {
        named("release") {
//            minifyEnabled false
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
        animationsDisabled = true
    }
}

dependencies {

    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.APP_COMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAINT_LAYOUT)

    // Retrofit
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_MOSHI)

    // Moshi
    implementation(Dependencies.MOSHI)
    implementation("androidx.test.espresso:espresso-core:3.4.0")
    kapt(Dependencies.MOSHI_KAPT)

    // Okhttp
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)

    // Lifecycle
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.LIFECYCLE_VIEWMODEL_KTX)

    // Coroutine
    implementation(Dependencies.COROUTINE_CORE)
    implementation(Dependencies.COROUTINE_ANDROID)

    // Coil
    implementation(Dependencies.COIL)

    // Recyclerview
    implementation(Dependencies.RECYCLERVIEW)

    // Navigation
    implementation(Dependencies.NAVIGATION_FRAGMENT_KTX)
    implementation(Dependencies.NAVIGATION_UI_KTX)

    // Room
    implementation(Dependencies.ROOM_RUNTIME)
    implementation(Dependencies.ROOM_KTX)
    kapt(Dependencies.ROOM_KAPT)
    // Room Paging
    implementation(Dependencies.ROOM_PAGING)

    // Kotlin serialization
    implementation(Dependencies.KOTLIN_SERIALIZATION)

    // DataStore
    implementation(Dependencies.PREFERENCES_DATASTORE)

    // Paging
    implementation(Dependencies.PAGING)

    // WorkManager
    implementation(Dependencies.WORKMANGER)

    // Hilt
    implementation(Dependencies.DAGGER_HILT)
    kapt(Dependencies.DAGGER_HILT_KAPT)

    // ViewModel delegate
    implementation(Dependencies.ACTIVITY_KTX)
    implementation(Dependencies.FRAGMENT_KTX)

    // Hilt extension
    implementation(Dependencies.HILT_EXTENSION_WORK)
    kapt(Dependencies.HILT_EXTENSION_KAPT)

    // Testing
    // 로컬 테스트용
    testImplementation(Testing.JUNIT4)
    // Assertion을 더 읽기 용이하게 해주는 Truth
    testImplementation("androidx.test.ext:truth:1.4.0")
    // 테스트를 크기에 따라서 필터링할 수 있게 하는 Runner
    testImplementation("androidx.test:runner:1.4.0")
    // Robolectric
    // JVM에서 사용되기 때문에 더 빠른 테스트를 수행할 수 있음
    testImplementation("org.robolectric:robolectric:4.8.1")
    testImplementation("androidx.test.ext:junit:1.1.3")
    testImplementation("androidx.test:core:1.4.0")
    // 코루틴 테스트를 위한 디펜던시
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2")

    // 계측 테스트용
    implementation(Testing.ANDROID_JUNIT)
    androidTestImplementation(Testing.ESPRESSO_CORE)
    // Espresso의 적용 대상을 확장하기 위한 디펜턴시
    androidTestImplementation("androidx.test.espresso:espresso-contrib:3.4.0")
    // Hamcrest - waitFor()사용을 위함
    androidTestImplementation("org.hamcrest:hamcrest:2.2")
    // Jetpack Test Core
    androidTestImplementation("androidx.test:core:1.4.0")
    // Assertion을 더 읽기 용이하게 해주는 Truth
    androidTestImplementation("androidx.test.ext:truth:1.4.0")
    // 테스트를 크기에 따라서 필터링할 수 있게 하는 Runner
    androidTestImplementation("androidx.test:runner:1.4.0")
    // 코루틴 테스트를 위한 디펜던시
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2")
    // 테스트에 hilt 적용을 위한 디펜던시
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.43.2")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.43.2")

}