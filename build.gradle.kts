buildscript {
    dependencies {
        classpath(libs.google.services)
        classpath(libs.secrets.gradle.plugin)
    }
}
// Top-level build file where you can add configuration options common to all subprojects/modules.
plugins {


    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.ksp) apply false
    id("com.google.firebase.crashlytics") version "3.0.3" apply false
}