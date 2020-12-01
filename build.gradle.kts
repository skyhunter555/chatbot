plugins {
    id("java")
    id("org.springframework.boot") version "2.1.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
}

group = "chatbot"
version = "1.0.0"

repositories {
    mavenCentral()
}

sourceSets["main"].java.srcDirs("src/main/java", "gen/main/java")

dependencies {
    implementation ("org.springframework.data:spring-data-commons:2.1.4.RELEASE")
    implementation ("org.springframework.data:spring-data-jpa:2.1.4.RELEASE")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa:2.1.4.RELEASE")
    implementation ("org.postgresql:postgresql:42.2.5")
    implementation ("org.liquibase:liquibase-core:3.6.3") {
        exclude(group = "ch.qos.logback", module = "logback-classic")
    }
    implementation ("org.yaml:snakeyaml:1.23")
    implementation ("org.springframework.boot:spring-boot-starter-webflux:2.1.4.RELEASE")
    implementation ("org.projectreactor:reactor-spring:1.0.1.RELEASE")
    implementation("io.github.lognet:grpc-spring-boot-starter:3.2.2")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.0.3.RELEASE")
    testImplementation("org.springframework.security:spring-security-test:4.0.0.RELEASE")
    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
