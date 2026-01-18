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
            fun getKeyFromFile(key: String, fileName: String = "local.properties"): String =
                System.getenv(key)
                    ?: run {
                        val file = rootDir.resolve(fileName)
                        if (!file.exists()) "" else java.util.Properties().apply {
                            file.inputStream().use { load(it) }
                        }.getProperty(key, "")
                    }
            credentials {
                username = getKeyFromFile("GITHUB_USER")
                password = getKeyFromFile("GITHUB_TOKEN")
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
            fun getKeyFromFile(key: String, fileName: String = "local.properties"): String =
                System.getenv(key)
                    ?: run {
                        val file = rootDir.resolve(fileName)
                        if (!file.exists()) "" else java.util.Properties().apply {
                            file.inputStream().use { load(it) }
                        }.getProperty(key, "")
                    }
            credentials {
                username = getKeyFromFile("GITHUB_USER")
                password = getKeyFromFile("GITHUB_TOKEN")
            }
        }
    }
}


rootProject.name = "AndroidUITest"
include(":app")
