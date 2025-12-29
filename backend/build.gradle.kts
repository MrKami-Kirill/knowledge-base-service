group = "ru.tecius"
version = "1.0.0"
description = "Knowledge Base Service Backend"

val serviceName = "${project.parent?.name}-${project.name}"

dependencies {
    api(project(":api"))
}

springBoot {
    buildInfo()
}

tasks.jar {
    enabled = false
}

tasks.bootJar {
    archiveClassifier.set("boot")
    archiveFileName.set("${project.parent?.name}-${project.name}-${version}.jar")
}

jib {
    setAllowInsecureRegistries(true)
    from {
        image = "gcr.io/distroless/java21-debian12:nonroot"
    }

    val registryImage = System.getenv("CI_REGISTRY_IMAGE")
    val isLocal = registryImage.isNullOrBlank()

    to {
        image = if (isLocal) {
            "localhost:5555/$serviceName:${version}"
        } else {
            "$registryImage/$serviceName:0.0.3"
        }
        tags = if (isLocal) {
            setOf("latest")
        } else {
            setOf(System.getenv("CI_COMMIT_SHORT_SHA") ?: "latest", "latest")
        }
        auth {
            username = if (isLocal) "registry_user" else System.getenv("CI_REGISTRY_USER") ?: ""
            password = if (isLocal) "mg3oZl3b9hZd123" else System.getenv("CI_JOB_TOKEN") ?: ""
        }
    }

    container {
        ports = listOf("8087")
        user = "nonroot:nonroot"
        creationTime.set("USE_CURRENT_TIMESTAMP")

        labels.set(
            mapOf(
                "maintainer" to "kgaleev@tecius.ru",
                "service" to serviceName,
                "version" to project.version.toString()
            )
        )
    }

    containerizingMode = "packaged"
}