plugins {
    id 'com.android.application'
}

android {
    compileSdkVersion 30
    buildToolsVersion "30.0.3"

    defaultConfig {
        applicationId "com.example.readordie"
        minSdkVersion 17
        targetSdkVersion 30
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    dependenciesInfo {
        includeInApk true
        includeInBundle true
    }
    ndkVersion '22.1.7171670'
}

dependencies {

    implementation 'com.github.jd-alexander:android-flat-button:v1.1'
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'

    implementation "androidx.vectordrawable:vectordrawable:1.1.0"

    /* To be able to animate properties of a VectorDrawable, add the following.  Useful for
     * illustration purposes or state changes in response to user events
     */
    implementation "androidx.vectordrawable:vectordrawable-animated:1.1.0"

    /* To use a seekable alternative for `androidx.vectordrawable:vectordrawable-animated` add the
     * following
     */
    implementation "androidx.vectordrawable:vectordrawable-seekable:1.0.0-alpha02"
    implementation 'androidx.navigation:navigation-fragment:2.3.5'
    implementation 'androidx.navigation:navigation-ui:2.3.5'
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0'

    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'


    //Add Library
    implementation 'com.google.firebase:firebase-core:18.0.2'
    implementation 'com.google.firebase:firebase-database:19.7.0'
