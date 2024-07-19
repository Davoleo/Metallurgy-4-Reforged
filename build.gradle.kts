import org.jetbrains.gradle.ext.Application
import org.jetbrains.gradle.ext.Gradle
import org.jetbrains.gradle.ext.RunConfigurationContainer
import java.io.FileWriter
import java.nio.file.Files

plugins {
    id("java-library")
    id("maven-publish")
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.1.8"
    id("eclipse")
    id("com.gtnewhorizons.retrofuturagradle") version "1.4.0"
}

//Project Properties
version = "${project.version}"
group = "it.hurts.metallurgy_reforged"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
        //Azul covers most of the platforms for Java 8 toolchains
        vendor.set(JvmVendorSpec.AZUL)
    }
    //Generate sources and javadocs jars when building and publishing
    withSourcesJar()
    withJavadocJar()
}

/**
 *  Most TFG configuration lives here [JavaDoc: com.gtnewhorizons.retrofuturagradle.MinecraftExtension ]
 */
minecraft {
    mcVersion.set("1.12.2")

    //Username for client run configurations
    //username.set("Developer")

    // Generate a field named VERSION with the mod version in the injected Tags class
    injectedTags.put("VERSION", project.version)

    // If you need the old replaceIn mechanism, prefer the injectTags task because it doesn't inject a javac plugin.
    // tagReplacementFiles.add("RfgExampleMod.java")

//    tagReplacementFiles.add("Metallurgy.java")
//    replace(Regex("@VERSION@"), project.version)


    // Enable assertions in the mod's package when running the client or server
    extraRunJvmArguments.add("-ea:${project.group}")

    // If needed, add extra tweaker classes like for mixins.
    // extraTweakClasses.add("org.spongepowered.asm.launch.MixinTweaker")

    // Exclude some Maven dependency groups from being automatically included in the reobfuscated runs
    groupsToExcludeFromAutoReobfMapping.addAll("com.diffplug", "com.diffplug.durian", "net.industrial-craft")

    useForgeEmbeddedMappings.set(false)

}

// Generates a class named rfg.examplemod.Tags with the mod version in it, you can find it at
tasks.injectTags.configure {
    outputClassName.set("${project.group}.Tags")
}

// Put the version from gradle into mcmod.info
tasks.processResources.configure {
    val projVersion = project.version.toString() // Needed for configuration cache to work
    inputs.property("version", projVersion)

    filesMatching("mcmod.info") {
        expand(mapOf("modVersion" to projVersion))
    }
}

// Create a new dependency type for runtime-only dependencies that don't get included in the maven publication
val runtimeOnlyNonPublishable: Configuration by configurations.creating {
    description = "Runtime only dependencies that are not published alongside the jar"
    isCanBeConsumed = false
    isCanBeResolved = false
}
listOf(configurations.runtimeClasspath, configurations.testRuntimeClasspath).forEach {
    it.configure {
        extendsFrom(
            runtimeOnlyNonPublishable
        )
    }
}

// Add an access tranformer
tasks.deobfuscateMergedJarToSrg.configure { accessTransformerFiles.from("src/main/resources/META-INF/metallurgy_at.cfg") }

repositories {

    //Maven central repository (Groovy)
    maven {
        name = "Maven Central"
        url = uri("https://repo.maven.apache.org/maven2")
    }

    //location of the maven that hosts JEI & TCon
    maven {
        name = "Progwml6 maven"
        url = uri("https://dvs1.progwml6.com/files/maven")
    }

    //JEI maven fallback
    maven {
        name = "ModMaven"
        url = uri("modmaven.k-4u.nl")
    }

    //Jared's Maven Repository (CraftTweaker)
    maven {
        name = "jared maven"
        url = uri("https://maven.blamejared.com/")
    }

    maven {
        name = "curse maven"
        url = uri("https://www.cursemaven.com")
    }

    //CurseForge maven as a final fallback (shouldn't be used at all as of this commit)
    maven {
        name = "CurseForge"
        url = uri("https://minecraft.curseforge.com/api/maven/")
    }
}

dependencies {

    //api = this is a dependency required both at runtime and at compile time. Moreover it is transitive (i.e. if A depends on B and B depends on C via api, then A can access C)
    //implementation = this is a dependency required both at runtime and at compile time. It is not transitive (i.e. if A depends on B and B depends on C via implementation, then A cannot access C)
    //runtimeOnly = this is a dependency only required at runtime. It won't be present at compile time
    //compileOnly = this is a dependency only required at compile time. It won't be present at runtime

    //compileOnly doesn't always stop things from loading at runtime

    //provided = same as compileOnly, don't use
    //compile = same as api, don't use
    //runtime = same as runtimeOnly, don't use

    implementation("org.codehaus.groovy:groovy-all:3.0.8")

    //Compile against the JEI API but do not include it at runtime
    compileOnly(rfg.deobf("mezz.jei:jei_1.12.2:4.15.+:api"))

    //At runtime, use the full JEI jar
    runtimeOnly(rfg.deobf("mezz.jei:jei_1.12.2:4.15.+"))

    //Tinker Construct & Mantle
    implementation(rfg.deobf("slimeknights.mantle:Mantle:1.12-1.3.3.56"))
    implementation(rfg.deobf("slimeknights:TConstruct:1.12.2-2.13.0.184"))

    compileOnly(rfg.deobf("curse.maven:constructs_armory_1.2.5.10-287683:3174535"))

    //Industrial Foregoing
    compileOnly(rfg.deobf("curse.maven:industrialforegoing_api-266515:2745323"))

    //CraftTweaker
    compileOnly(rfg.deobf("CraftTweaker2:CraftTweaker2-API:4.1.20.631"))
    //compileOnly 'CraftTweaker2:CraftTweaker2-MC1120-Main:1.12-4.1.20.586'

    //ProjectE
    compileOnly(rfg.deobf("curse.maven:projecte_1.4.1-226410:2702991"))

    //Silent's Gems
    compileOnly(rfg.deobf("curse.maven:silent_lib_3.0.14+168-242998:2851111"))
    compileOnly(rfg.deobf("curse.maven:silents_gems_2.8.22+237-220311:2804966"))

    //Immersive Engineering
    compileOnly(rfg.deobf("curse.maven:immersive_engineering_0.12_98-231951:2974106"))

    //Spartan Weaponry
    compileOnly(rfg.deobf("curse.maven:spartanweaponry_1.5.3-278141:4467344"))
}


//jar {
//    destinationDir = file("output")
//}
//
//sourceJar {
//    destinationDir = file("output")
//}

//clean {
//    delete("output")
//    delete("mcmodsrepo")
//}

//Used to stop the server just after it's started correctly if we're in a CI Environment
tasks.register("setupCiEnv") { //task ->
    //task.group = "build setup"
    //task.description = "setups files to run CI Tests"

    doFirst {
        val ciEnvFile = File(projectDir.getPath() + File.separatorChar + "ci_env")
        if (!ciEnvFile.exists())
            Files.createFile(ciEnvFile.toPath())

        val eula = File("${projectDir.getPath()}/run")
        eula.mkdir()
        val writer = FileWriter("${eula.getPath()}/eula.txt")
        writer.write("eula=true")
        writer.close()
    }
}

// IDE Settings
eclipse {
    classpath {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
        inheritOutputDirs = true // Fix resources in IJ-Native runs
    }
    project {
        this.withGroovyBuilder {
            "settings" {
                "runConfigurations" {
                    val self = this.delegate as RunConfigurationContainer
                    self.add(Gradle("1. Run Client").apply {
                        setProperty("taskNames", listOf("runClient"))
                    })
                    self.add(Gradle("2. Run Server").apply {
                        setProperty("taskNames", listOf("runServer"))
                    })
                    self.add(Gradle("3. Run Obfuscated Client").apply {
                        setProperty("taskNames", listOf("runObfClient"))
                    })
                    self.add(Gradle("4. Run Obfuscated Server").apply {
                        setProperty("taskNames", listOf("runObfServer"))
                    })

                    //These require extra configuration in IntelliJ, so are not enabled by default
                    self.add(Application("Run Client (IJ Native, Deprecated)", project).apply {
                        mainClass = "GradleStart"
                        moduleName = project.name + ".ideVirtualMain"
                        afterEvaluate {
                            val runClient = tasks.runClient.get()
                            workingDirectory = runClient.workingDir.absolutePath
                            programParameters = runClient.calculateArgs().map { '"' + it + '"' }.joinToString(" ")
                            jvmArgs = runClient.calculateJvmArgs().map { '"' + it + '"' }.joinToString(" ") +
                                    ' ' + runClient.systemProperties.map { "\"-D" + it.key + '=' + it.value.toString() + '"' }
                                .joinToString(" ")
                        }
                    })
                    self.add(Application("Run Server (IJ Native, Deprecated)", project).apply {
                        mainClass = "GradleStartServer"
                        moduleName = project.name + ".ideVirtualMain"
                        afterEvaluate {
                            val runServer = tasks.runServer.get()
                            workingDirectory = runServer.workingDir.absolutePath
                            programParameters = runServer.calculateArgs().map { '"' + it + '"' }.joinToString(" ")
                            jvmArgs = runServer.calculateJvmArgs().map { '"' + it + '"' }.joinToString(" ") +
                                    ' ' + runServer.systemProperties.map { "\"-D" + it.key + '=' + it.value.toString() + '"' }
                                .joinToString(" ")
                        }
                    })
                }
                "compiler" {
                    val self = this.delegate as org.jetbrains.gradle.ext.IdeaCompilerConfiguration
                    afterEvaluate {
                        self.javac.moduleJavacAdditionalOptions = mapOf(
                            (project.name + ".main") to
                                    tasks.compileJava.get().options.compilerArgs.map { '"' + it + '"' }
                                        .joinToString(" ")
                        )
                    }
                }
            }
        }
    }
}

tasks.processIdeaSettings.configure {
    dependsOn(tasks.injectTags)
}

/*==============================================================================
 = Class: build
 = This class is part of Metallurgy 4: Reforged
 = Complete source code is available at https://github.com/Davoleo/Metallurgy-4-Reforged
 = This code is licensed under GNU GPLv3
 = Authors: Davoleo, ItHurtsLikeHell, PierKnight100
 = Copyright (c) 2018-2021.
 =============================================================================*/


//Maven Repository
publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "local"
            url = uri("file:///${project.projectDir.toString().replace('\\', '/')}/mcmodsrepo")
        }

        maven {
            name = "github"
            url = uri("https://maven.pkg.github.com/Davoleo/Metallurgy-4-Reforged")
            credentials {
                username = ""
                password = ""
            }
        }
    }
}