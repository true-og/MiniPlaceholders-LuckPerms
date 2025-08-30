plugins {
    id("fabric-loom")
    id("com.gradleup.shadow") version "8.3.6" // Import Shadow plugin.
}

val shade: Configuration by configurations.creating

dependencies {
    compileOnly(libs.luckperms)
    compileOnly(libs.miniplaceholders)
    implementation(projects.luckpermsExpansionCommon)
    shade(projects.luckpermsExpansionCommon)
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
    modImplementation(libs.adventure.platform.fabric)
}

tasks {
    processResources {
        filteringCharset = Charsets.UTF_8.name()
        filesMatching("fabric.mod.json") {
            expand("version" to project.version)
        }
    }
    remapJar {
        inputFile.set(shadowJar.get().archiveFile)
        archiveFileName.set("MiniPlaceholders-LuckPerms-Expansion-Fabric-${project.version}.jar")
        doLast {
            copy {
                from(archiveFile)
                into("${rootProject.projectDir}/build")
            }
        }
    }
    shadowJar {
        configurations = listOf(shade)
    }
}

java {
    withSourcesJar()
}
