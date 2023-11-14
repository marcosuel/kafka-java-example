plugins {
	java
	id("org.springframework.boot") version "2.6.1"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	id("com.github.davidmc24.gradle.plugin.avro") version "1.3.0"
}

group = "kafkajavalistener"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	gradlePluginPortal()
	maven {
		url = uri("https://maven.repository.redhat.com/earlyaccess/all/")
		isAllowInsecureProtocol = true
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("io.confluent:kafka-avro-serializer:5.3.0")
	implementation("org.apache.avro:avro:1.11.0")
	implementation("org.springframework.boot:spring-boot-starter-json:3.1.5")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
