plugins {
    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.2.3")
    compileOnly("org.projectlombok:lombok:1.18.16")
    annotationProcessor("org.projectlombok:lombok:1.18.16")
    testCompileOnly("org.projectlombok:lombok:1.18.16")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.16")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

val theMain by extra("me.anandsharma.AppJava")

application {
    mainClass.set(theMain)
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes(
                "Implementation-Title" to "Gradle",
                "Implementation-Version" to archiveVersion,
                "Main-Class" to theMain
        )
    }
}

tasks.register<Jar>("uberJar") {
    archiveClassifier.set("uber")
    manifest {
        attributes(
                "Implementation-Title" to "Gradle",
                "Implementation-Version" to archiveVersion,
                "Main-Class" to theMain
        )
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

