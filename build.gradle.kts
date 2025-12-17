group = "ru.tecius"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

val coverageExclusions: Array<String> by extra(
    arrayOf(
        // TODO Дополнить exclusions
        "**/ru/tecius/Application.*",

    )
)

val sonarExclusions: Array<String> by extra(
    arrayOf(
        "**/build.gradle.kts",
        "**/settings.gradle.kts",
    )
)

val log4j2version: String by project
val jclOverSlf4jVersion: String by project
val postgresqlVersion: String by project
val springDocOpenApiVersion: String by project
val commonsCollections4Version: String by project
val micrometerRegistryPrometheusVersion: String by project
val mapstructVersion: String by project
val jacocoVersion: String by project
val checkstyleVersion: String by project
val minioVersion: String by project

plugins {
    java
    `java-library`
    checkstyle
    jacoco
    // https://plugins.gradle.org/plugin/org.springframework.boot
    id("org.springframework.boot") version "3.5.8" apply false
    // https://plugins.gradle.org/plugin/io.spring.dependency-management
    id("io.spring.dependency-management") version "1.1.7" apply false
    // https://plugins.gradle.org/plugin/org.sonarqube
    id("org.sonarqube") version "7.2.1.6560"
    // https://plugins.gradle.org/plugin/com.google.cloud.tools.jib
    id("com.google.cloud.tools.jib") version "3.5.2" apply false
}

subprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    buildscript {
        configurations.all {
            resolutionStrategy {
                // https://mvnrepository.com/artifact/com.fasterxml.jackson/jackson-bom
                force("com.fasterxml.jackson.core:jackson-core:2.20.1")
            }
        }

        repositories {
            mavenCentral()
            gradlePluginPortal()
        }
    }

    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "checkstyle")
    apply(plugin = "jacoco")

    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.sonarqube")
    apply(plugin = "com.google.cloud.tools.jib")

    dependencies {
        // LOG4J
        implementation("org.springframework.boot:spring-boot-starter-log4j2")
        implementation("org.apache.logging.log4j:log4j-spring-boot")
        implementation("org.apache.logging.log4j:log4j-layout-template-json")
        implementation("org.slf4j:jcl-over-slf4j:$jclOverSlf4jVersion")

        // SPRING
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        // POSTGRES
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.postgresql:postgresql:$postgresqlVersion")

        // MINIO
        implementation("io.minio:minio:$minioVersion")

        // REDIS
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
        // implementation("org.springframework.session:spring-session-data-redis")

        // ELASTIC SEARCH
        // implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")

        // KAFKA
        implementation("org.springframework.kafka:spring-kafka")

        // SWAGGER
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springDocOpenApiVersion")

        // OTHER
        implementation("org.apache.commons:commons-collections4:$commonsCollections4Version")
        implementation("io.micrometer:micrometer-registry-prometheus:$micrometerRegistryPrometheusVersion")
        implementation("org.mapstruct:mapstruct:$mapstructVersion")
        compileOnly("org.projectlombok:lombok")
        compileOnly("org.mapstruct:mapstruct-processor:$mapstructVersion")
        annotationProcessor("org.projectlombok:lombok")
        annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

        // TEST
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    checkstyle {
        toolVersion = checkstyleVersion
        configFile = file("${rootDir}/docs/checkstyle/google_checks.xml")
        maxErrors = 0
        maxWarnings = 0
    }

    jacoco {
        toolVersion = jacocoVersion
    }

    configurations {
        all {
            exclude("commons-logging")
            exclude("org.springframework.boot", "spring-boot-starter-logging")
            resolutionStrategy.eachDependency {
                if (requested.group == "org.apache.logging.log4j") {
                    useVersion(log4j2version)
                }
            }
        }
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    tasks.processResources {
        filesMatching("application.yml") {
            expand(project.properties)
        }
    }

    tasks.check {
        dependsOn("checkstyleMain")
    }

    tasks.jacocoTestReport {
        afterEvaluate {
            classDirectories.setFrom(files(classDirectories.files.map {
                fileTree(it) {
                    exclude(*coverageExclusions)
                }
            }))
        }
        dependsOn(tasks.test)
        reports {
            csv.required.set(true)
            html.required.set(true)
            xml.required.set(true)
        }
    }

    tasks.compileJava {
        options.encoding = "UTF-8"
    }

    tasks.javadoc {
        options.encoding = "UTF-8"
    }

    tasks.test {
        finalizedBy(tasks.jacocoTestReport)
        useJUnitPlatform()
    }

}

sonar {
    properties {
        property("sonar.java.binaries", "**/classes")
        property("sonar.java.libraries", "build/libs")
        property("sonar.coverage.exclusions", coverageExclusions.joinToString(","))
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
        property("sonar.exclusions", sonarExclusions.joinToString(","))
        property("sonar.junit.reportPaths", "build/test-results/test")
    }
}

tasks.sonar {
    dependsOn(tasks.build)
    dependsOn(tasks.test)
}