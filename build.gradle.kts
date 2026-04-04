// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.build.logic) apply false
    alias(libs.plugins.sonar.config) apply false
    id("io.github.takahirom.roborazzi") version "1.59.0" apply false
}