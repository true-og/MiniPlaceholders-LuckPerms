enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "MiniPlaceholders-LuckPerms"

dependencyResolutionManagement {
    repositories {
        maven("https://papermc.io/repo/repository/maven-public/")
        //maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

arrayOf("common", "paper").forEach {
    include("luckperms-expansion-$it")
    project(":luckperms-expansion-$it").projectDir = file(it)
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
    id("org.spongepowered.gradle.plugin") version "2.2.0"
}
