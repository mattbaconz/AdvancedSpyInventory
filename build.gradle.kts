plugins {
    java
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.21"
    id("com.gradleup.shadow") version "9.2.2"
}

group = "mc.dailycraft"
version = "2.4.13"
description = "Open and modify the inventory of entities and players, and the ender chest of the players, online or not!"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    paperweight.paperDevBundle("26.3.build.242-stable")
    implementation("org.bstats:bstats-bukkit:3.1.0")
}

java.toolchain.languageVersion = JavaLanguageVersion.of(25)

sourceSets.main {
    java.setSrcDirs(listOf("main/src/main/java", "v26_3/src/main/java"))
    resources.setSrcDirs(listOf("main/src/main/resources"))
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.processResources {
    val properties = mapOf(
        "parent" to mapOf("name" to project.name, "url" to "https://www.curseforge.com/minecraft/bukkit-plugins/advancedspyinventory"),
        "description" to project.description,
        "version" to project.version,
        "minecraftVersion" to "26.3",
        "groupId" to project.group
    )
    inputs.properties(properties)
    filesMatching("plugin.yml") { expand(properties) }
}

tasks.jar { enabled = false }

tasks.shadowJar {
    archiveClassifier = ""
    relocate("org.bstats", "mc.dailycraft.bstats")
    manifest.attributes["paperweight-mappings-namespace"] = "mojang"
}

tasks.assemble { dependsOn(tasks.shadowJar) }
