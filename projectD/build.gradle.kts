plugins {
    `java-library`
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-lang3:3.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

configurations.all {
    resolutionStrategy.dependencySubstitution {
        substitute(module("org.apache.commons:commons-lang3")) // let's pretend that this is projectA jar published to maven central.
            .using(project(":projectA"))
            .because("why not")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}