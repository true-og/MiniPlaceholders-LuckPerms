plugins {
    java
	eclipse
    id("com.gradleup.shadow") version "8.3.6" // Import Shadow plugin.
}

dependencies {
    implementation(projects.luckpermsExpansionPaper)
    implementation(projects.luckpermsExpansionVelocity)
    implementation(projects.luckpermsExpansionSponge)
}

subprojects {
    apply<JavaPlugin>()

    java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

    tasks {
        compileJava {
            options.encoding = Charsets.UTF_8.name()
            options.release.set(17)
        }
    }
}

tasks {
    shadowJar {
        archiveFileName.set("MiniPlaceholders-LuckPerms-Expansion-${project.version}.jar")
        archiveClassifier.set("")
        doLast {
            copy {
                from(archiveFile)
                into("${rootProject.projectDir}/build")
            }
        }
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    build {
        dependsOn(shadowJar)
    }
}
