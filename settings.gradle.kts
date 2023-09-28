pluginManagement {
    repositories {
        google()
        jcenter()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri(path = "https://www.jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    this.repositories {
        google()
        jcenter()
        mavenCentral()
        maven { url = uri(path = "https://www.jitpack.io") }
    }
}

rootProject.name = "Hydrogen"
include(":app")
include(":library")
