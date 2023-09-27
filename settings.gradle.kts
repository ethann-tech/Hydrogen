pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri(path = "https://www.jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    this.repositories {
        google()
        mavenCentral()
        maven { url = uri(path = "https://www.jitpack.io") }
    }
}

rootProject.name = "Hydrogen"
include(":app")
include(":library")
