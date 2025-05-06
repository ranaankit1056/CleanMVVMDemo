
    //--coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    //-- ViewModel & Livedata
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.7")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

    //--- Dagger Dagger-2
    implementation("com.google.dagger:dagger:2.52")
    kapt("com.google.dagger:dagger-compiler:2.52")
    implementation("com.google.dagger:dagger-android:2.50")
    kapt("com.google.dagger:dagger-android-processor:2.50")

    // Hilt for dependency injection
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.52")


    //--serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    //--okhttp3
    implementation("com.squareup.okhttp3:okhttp:2.7.5")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    //-- retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")