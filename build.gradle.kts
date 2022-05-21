import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.10.0"
    application
    `java-library`
    `maven-publish`
}

group = "dev.patbeagan1"
version = "0.3-SNAPSHOT"

java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.3")
    // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "protocol-rss"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set("ProtocolRSS")
                description.set("A kotlin implementation of the RSS protocol. Derived from https://www.rssboard.org/rss-specification")
                url.set("https://github.com/patbeagan1/ProtocolRSS")
                licenses {
                    license {
                        name.set("The MIT License (MIT)")
                        url.set("https://mit-license.org/")
                    }
                }
                developers {
                    developer {
                        id.set("patbeagan1")
                    }
                }
                scm {
                    connection.set("scm:git:git://git@github.com:patbeagan1/ProtocolRSS.git")
                    developerConnection.set("scm:git:ssh://git@github.com:patbeagan1/ProtocolRSS.git")
                    url.set("https://github.com/patbeagan1/ProtocolRSS")
                }
            }
        }
    }
}
