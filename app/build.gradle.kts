import com.baga.androidapp.androiddevelopmentteam.build_logic.quality.KoverDetails
import java.util.Properties
import java.io.BufferedReader

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    alias(libs.plugins.hilt)
    id("com.baga.androidapp.androiddevelopmentteam.build_logic.quality")
}

android {
    namespace = "com.cicdanduitest.androiduitest"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.cicdanduitest.androiduitest"
        minSdk = 23
        targetSdk = 36
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

    implementation(libs.retrofit)
    // gson converter
    implementation(libs.converter.gson)

    testImplementation("io.mockk:mockk:1.13.10")        // MockK for mocking
    testImplementation("com.google.truth:truth:1.4.2")   // Google Truth for assertions
    testImplementation("junit:junit:4.13.2")             // JUnit 4
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")

    // (Optional) For Android instrumentation tests
    androidTestImplementation("io.mockk:mockk-android:1.13.10")
    androidTestImplementation("com.google.truth:truth:1.4.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

fun Project.getLocalProperty(key: String, file: String = "local.properties"): String {
    val properties = Properties()
    properties.load(project.rootProject.file(file).reader())
    return properties.getProperty(key)
}

fun getCurrentGitBranch(): String {
    val process = ProcessBuilder(
        "git", "rev-parse", "--abbrev-ref", "HEAD"
    )
        .redirectErrorStream(true)
        .start()

    val output = process.inputStream.bufferedReader().use(BufferedReader::readText)

    val exitCode = process.waitFor()
    if (exitCode != 0) {
        throw IllegalStateException("Failed to get git branch (exit code=$exitCode)")
    }

    return output.trim()
}

tasks.register("codeCheck") {
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


tasks.register("unitTestCoverage") {
    group = "verification"
    description = "Generates the Kover XML report and then runs SonarQube analysis."

    dependsOn(
        "lintDebug",
        ":app:koverHtmlReport",
        ":app:koverXmlReport",
        ":app:sonar"
    )
}

quality {
    this.enableSpotless = false
    this.enableSonar = true
    this.sonarHostUrl = getLocalProperty("sonar.url", "config.properties")
    this.sonarProjectKey = getLocalProperty("sonar.key", "config.properties")
    this.sonarOrganization = getLocalProperty("sonar.organization", "config.properties")
    this.sonarProjectName = getLocalProperty("sonar.name", "config.properties")
    this.sonarToken =
        System.getenv("SONAR_TOKEN") ?: getLocalProperty("sonar.token", "config.properties")
    this.sonarXmlReportPaths = "${project.buildDir}/reports/kover/xml/report.xml"
    this.sonarExclusions = "**/*Fragment.kt, **/*Activity*,**/ui/theme/*.kt"
    val url: String = getLocalProperty("sonar.url", "config.properties").toString()
    if (!url.contains("https://sonarcloud.io")) {
        // Fetch the current Git branch name for community edition
        val branchName = System.getenv("CURRENT_BRANCH") ?: getCurrentGitBranch()
        this.sonarBranchName = branchName
    }

    this.koverConfigs = KoverDetails.Builder()
        .setExcludedClasses(listOf("*Fragment*", "*Activity*")).build()
}