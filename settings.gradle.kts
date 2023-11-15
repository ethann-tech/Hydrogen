pluginManagement {
    repositories {
        maven { url = uri(path = "https://www.jitpack.io") }
        google()
        mavenCentral()
        gradlePluginPortal()

    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    this.repositories {
        maven { url = uri(path = "https://www.jitpack.io") }
        google()
        mavenCentral()
    }
}

rootProject.name = "Hydrogen"
include(":app")
include(":library")
