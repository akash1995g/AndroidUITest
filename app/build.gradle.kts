import java.util.Properties
import java.io.ByteArrayOutputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.spotless)
    alias(libs.plugins.sonarqube)
    id("kotlin-kapt")
    alias(libs.plugins.kover)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.cicdanduitest.androiduitest"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.cicdanduitest.androiduitest"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
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
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.savedstate)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}

spotless {
    java {
        target("**/*.java")
        googleJavaFormat().aosp()
        removeUnusedImports()
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
    }
    kotlin {
        target("**/*.kt")
        trimTrailingWhitespace()
        ktlint()
        indentWithSpaces()
        endWithNewline()
    }
}

sonar {
    properties {
        property("sonar.projectKey", getLocalProperty("sonar.key", "config.properties"))
        property("sonar.projectName", getLocalProperty("sonar.name", "config.properties"))
        property("sonar.host.url", getLocalProperty("sonar.url", "config.properties"))
        property(
            "sonar.token",
            System.getenv("SONAR_TOKEN") ?: getLocalProperty("sonar.token", "config.properties")
        )
        property("sonar.organization", getLocalProperty("sonar.organization", "config.properties"))
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "${project.buildDir}/reports/kover/xml/report.xml"
        )
        property("sonar.coverage.exclusions", "**/*Fragment.kt, **/*Activity*,**/ui/theme/*.kt")
        val url: String = getLocalProperty("sonar.url", "config.properties").toString()
        if (!url.contains("https://sonarcloud.io")) {
            // Fetch the current Git branch name for community edition
            val branchName = System.getenv("CURRENT_BRANCH") ?: getCurrentGitBranch()
            property("sonar.branch.name", branchName)
        }
    }
}

fun Project.getLocalProperty(key: String, file: String = "local.properties"): Any {
    val properties = Properties()
    properties.load(project.rootProject.file(file).reader())
    return properties.getProperty(key)
}

fun getCurrentGitBranch(): String {
    val stdout = ByteArrayOutputStream()
    exec {
        commandLine("git", "rev-parse", "--abbrev-ref", "HEAD")
        standardOutput = stdout
    }
    return stdout.toString().trim()
}


kover {
    filters {
        classes {
            excludes.addAll(listOf("*Fragment*", "*Activity*"))
        }
        annotations {
            excludes.addAll(listOf("*Generated", "*CustomAnnotationToExclude"))
        }
    }
    verify {
        rule {
            //            isEnabled = true
            /* name = "Coverage must be more than 60%"
             bound {
                 minValue = 60
             }*/
        }
    }

    htmlReport {
        onCheck.set(false)
    }
    xmlReport {
        onCheck.set(false)
    }
}

task("codeCheck") {
    group = "verification"
    description = "Generates the Kover XML report and then runs SonarQube analysis."

    dependsOn(
        ":app:spotlessApply",
        "lint",
        ":app:koverHtmlReport",
        ":app:koverXmlReport",
        ":app:sonar"
    )
}


task("unitTestCoverage") {
    group = "verification"
    description = "Generates the Kover XML report and then runs SonarQube analysis."

    dependsOn(
        "lintDebug",
        ":app:koverHtmlReport",
        ":app:koverXmlReport",
        ":app:sonar"
    )
}