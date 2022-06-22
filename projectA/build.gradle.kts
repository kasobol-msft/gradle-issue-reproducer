plugins {
    `java-library`
}

version = "unspecified"

repositories {
    mavenCentral()
}


sourceSets {
    create("extrasrc") {
        java {
            setSrcDirs(listOf("src/extrasrc/java"))
        }
    }
}

configurations.create("customConfiguration") {
    extendsFrom(
        configurations.getByName("default"),
        configurations.getByName("extrasrcImplementation"),
        configurations.getByName("extrasrcRuntimeOnly")
    )
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

// Make jar compose both main and extrasrc.
tasks.getByName<Jar>("jar") {
    from(tasks.getByName("compileExtrasrcJava"))
}
