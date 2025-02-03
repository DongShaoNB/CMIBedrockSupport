plugins {
    kotlin("jvm") version "2.1.0"
    id("com.gradleup.shadow") version "8.3.6"
}

group = "cc.dsnb"
version = "2.0.4"

repositories {
    mavenCentral()
    maven {
        // PaperAPI
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        // Floodgate
        name = "opencollab-snapshot"
        url = uri("https://repo.opencollab.dev/main/")
    }
    maven {
        // EssentialsX
        name = "essentialsx"
        url = uri("https://repo.essentialsx.net/releases/")
    }
    maven {
        // HuskHomes
        name = "william278.net"
        url = uri("https://repo.william278.net/releases")
    }
    maven {
        // AuthMe
        name = "codemc-repo"
        url = uri("https://repo.codemc.org/repository/maven-public/")
    }
    maven {
        // PlaceholderAPI
        name = "placeholderapi"
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
    maven {
        // UniversalScheduler
        name = "jitpack"
        url = uri("https://jitpack.io")
    }
}

dependencies {
    testImplementation(kotlin("test"))
    // PaperAPI
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    // Floodgate
    compileOnly("org.geysermc.floodgate:api:2.2.3-SNAPSHOT")
    // CMILib
    compileOnly(files("lib/CMILib1.4.7.15.jar"))
    // CMI-API
    compileOnly(files("lib/CMI-API9.7.0.1.jar"))
    // EssentialsX
    compileOnly("net.essentialsx:EssentialsX:2.20.1")
    // HuskHomes
    compileOnly("net.william278.huskhomes:huskhomes-bukkit:4.9")
    // AdvancedTeleport
    compileOnly(files("lib/AdvancedTeleport-Bukkit-6.1.1-all.jar"))
    // nightcore
    compileOnly(files("lib/nightcore-2.6.3.jar"))
    // SunLight
    compileOnly(files("lib/SunLight-3.12.1.jar"))
    // AuthMe
    compileOnly("fr.xephi:authme:5.7.0-SNAPSHOT")
    // CatSeedLogin
    compileOnly(files("lib/CatSeedLogin-1.4.1-SNAPSHOT.jar"))
    // NexEngine (use NexAuth API to login or register player depend on NexEngine)
    compileOnly(files("lib/NexEngine.jar"))
    // NexAuth
    compileOnly(files("lib/NexAuth-2.0.5.jar"))
    // PlaceholderAPI
    compileOnly("me.clip:placeholderapi:2.11.6")
    // bStats
    implementation("org.bstats:bstats-bukkit:3.1.0")
    // DazzleConf
    implementation("space.arim.dazzleconf:dazzleconf-ext-snakeyaml:1.3.0-M2")
    // UniversalScheduler
    implementation("com.github.Anon8281:UniversalScheduler:0.1.6")
}

tasks {
    shadowJar {
        // bStats
        relocate("org.bstats", "cc.dsnb.bedrockplayersupport.bstats")
        // DazzleConf
        relocate("space.arim.dazzleconf", "cc.dsnb.bedrockplayersupport.dazzleconf")
        // UniversalScheduler
        relocate(
            "com.github.Anon8281.universalScheduler",
            "cc.dsnb.bedrockplayersupport.universalScheduler"
        )
    }
    processResources {
        filesMatching("plugin.yml") {
            expand("version" to rootProject.version)
        }
    }
}
kotlin {
    jvmToolchain(17)
}
