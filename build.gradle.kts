plugins {
    java
    kotlin("jvm") version "1.3.72"
    application
}

application {
    mainClassName = "ee.schimke.AgentMain"
}

group = "ee.schimke"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.jar {
    manifest {
        attributes(
            "Agent-Class" to "ee.schimke.Agent",
            "Can-Redefine-Classes" to "true",
            "Can-Retransform-Classes" to "true"
        )
    }
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.7.2")
}
