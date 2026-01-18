pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://maven.pkg.github.com/akash1995g/AndroidLibrary")
            val localProps = java.util.Properties().apply {
                val file = rootDir.resolve("local.properties")
                if (file.exists()) {
                    load(file.inputStream())
                }
            }
            credentials {
                username = localProps.getProperty("GITHUB_USER") ?: ""
                password = localProps.getProperty("GITHUB_TOKEN") ?: ""
            }
        }
        mavenLocal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/akash1995g/AndroidLibrary")
            val localProps = java.util.Properties().apply {
                val file = rootDir.resolve("local.properties")
                if (file.exists()) {
                    load(file.inputStream())
                }
            }
            credentials {
                username = localProps.getProperty("GITHUB_USER") ?: ""
                password = localProps.getProperty("GITHUB_TOKEN") ?: ""
            }
        }
    }
}


rootProject.name = "AndroidUITest"
include(":app")
