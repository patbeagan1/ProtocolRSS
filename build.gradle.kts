import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL

plugins {
    kotlin("jvm") version "1.6.21"
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
    id("org.jetbrains.kotlinx.binary-compatibility-validator") version "0.10.0"
    id("org.jetbrains.dokka") version "1.6.21"
    application
    jacoco
    `java-library`
    `maven-publish`
}

// /////////////////////////
group = "dev.patbeagan1"
version = "0.3.1"
// /////////////////////////

repositories {
    mavenCentral()
}

dependencies {
    api("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.2") // https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter
}

java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
    kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
}

/**
 * https://github.com/Kotlin/dokka
 * https://kotlin.github.io/dokka/1.5.30/user_guide/gradle/usage/
 */
tasks.dokkaHtml {
    outputDirectory.set(projectDir.resolve("docs"))
    dokkaSourceSets {
        configureEach {
            reportUndocumented.set(true)
            skipEmptyPackages.set(true)
            sourceLink {
                localDirectory.set(projectDir.resolve("src/main/kotlin"))
                remoteUrl.set(
                    URL("https://github.com/patbeagan1/ProtocolRSS/blob/master/src/main/kotlin")
                )
                remoteLineSuffix.set("#L")
            }
            jdkVersion.set(11)
        }
    }
}

application {
    mainClass.set("MainKt")
}

jacoco {
    toolVersion = "0.8.7"
    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.check {
    dependsOn(tasks.jacocoTestCoverageVerification)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.register("openJacocoReport") {
    dependsOn(tasks.jacocoTestReport)
    doLast {
        val root = projectDir.path
        exec {
            commandLine("firefox", "$root/build/jacocoHtml/index.html")
        }
    }
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.80".toBigDecimal()
            }
        }

        rule {
            isEnabled = false
            element = "CLASS"
            includes = listOf("org.gradle.*")

            limit {
                counter = "LINE"
                value = "TOTALCOUNT"
                maximum = "0.3".toBigDecimal()
            }
        }
    }
}

val githubCredentials: MavenArtifactRepository.() -> Unit = {
    // property values are defined in ~/.gradle/gradle.properties
    // environment variables can be stored in your ~/.bashrc or ~/.zshrc file.
    credentials {
        // this is your github username
        username = project.findProperty("gpr.username") as String? ?: System.getenv("GITHUB_USERNAME")
        // this is your personal access token. You can generate a new one here: https://github.com/settings/tokens
        password = project.findProperty("gpr.personal_access_token") as String? ?: System.getenv("GITHUB_PERSONAL_ACCESS_TOKEN")
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/patbeagan1/ProtocolRSS")
            githubCredentials()
        }
    }
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
                url.set("https://maven.pkg.github.com/patbeagan1/ProtocolRSS")
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
