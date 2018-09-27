import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "ru.siksmfp.kotlin.hibernate"
version = "0.0.1-SNAPSHOT"

plugins {
    val kotlinVersion = "1.2.51"
    val springBootVersion = "2.0.5.RELEASE"
    id("org.springframework.boot") version springBootVersion
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("io.spring.dependency-management") version "1.0.5.RELEASE"
}

repositories {
    mavenCentral()
}

val test by tasks.getting(Test::class) {
    useJUnitPlatform()
}

dependencies {
    val kotlinVersion = "1.2.51"
    val springBootVersion = "2.0.5.RELEASE"

    compile("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    compile("com.h2database:h2:1.4.197")
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    compile("org.jetbrains.kotlin:kotlin-reflect")
    compile("org.projectlombok:lombok:1.18.2")
    testCompile("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "junit")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}