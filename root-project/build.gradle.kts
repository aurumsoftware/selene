import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com.aurum.services.document"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    //	LOG4J
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
    implementation(project(":security"))
    implementation(project(":http"))
    implementation(project(":queue"))
    implementation(project(":controller"))
    implementation(project(":application"))
    implementation(project(":repository"))
    implementation(project(":domain"))
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
