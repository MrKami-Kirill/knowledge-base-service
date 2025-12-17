group = "ru.tecius"
version = "1.0.0"
description = "Knowledge Base Backend Service"

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
