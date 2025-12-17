group = "ru.tecius"
version = "1.0.0"
description = "Knowledge Base API"

tasks.jar {
    archiveFileName.set("${project.parent?.name}-${project.name}-${version}.jar")
}

tasks.bootJar {
    enabled = false
}