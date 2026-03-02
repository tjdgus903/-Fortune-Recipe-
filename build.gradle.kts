import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22" // JPA 엔티티용
    kotlin("plugin.allopen") version "1.9.22" // 프록시 생성을 위해 클래스를 열어줌
}

group = "com.fortune"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // Web & Backend
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    // Database & ORM
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")

    // Validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Utility (Lunar Calendar - 사주 계산용 라이브러리)
    implementation("com.github.shin285:KOMORAN:3.3.4") // 필요시 형태소 분석이나 만세력 라이브러리 추가
    implementation("icu.utitest:lunar-calendar:1.0.0") // 예시: 음양력 변환 라이브러리

    // Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// JPA 관련 All-Open 설정 (JPA 엔티티에 open 키워드를 생략할 수 있게 해줌)
allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}