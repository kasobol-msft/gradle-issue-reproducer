plugins {
    `java-library`
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":projectA", "customConfiguration")) // wont compile without "customConfiguration"
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}