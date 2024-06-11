import org.jooq.meta.jaxb.ForcedType
import org.jooq.meta.jaxb.Logging
import org.jooq.meta.jaxb.Property

plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    id("nu.studer.jooq") version "9.0"
    id("org.liquibase.gradle") version "2.2.1"
}


group = "by.grsu"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

liquibase {
    activities.register("main") {
        this.arguments = mapOf(
            "changeLogFile" to "src/main/resources/db/changes/db.changelog-master.xml",
            "url" to "jdbc:postgresql://localhost:5432/skydiving",
            "username" to "postgres",
            "password" to "postgres"
        )
    }
    runList = "main"
}

jooq {
    version.set("3.18.14")
    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = (findProperty("SPRING_DATASOURCE_URL")
                        ?: "jdbc:postgresql://localhost:5432/skydiving").toString()
                    user = (findProperty("SPRING_DATASOURCE_USERNAME")
                        ?: "postgres").toString()
                    password = (findProperty("SPRING_DATASOURCE_PASSWORD")
                        ?: "postgres").toString()
                    properties = listOf(
                        Property().apply {
                            key = "PAGE_SIZE"
                            value = "2048"
                        }
                    )
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "public"
                        excludes = "databasechangelog|databasechangeloglock"
                        forcedTypes = listOf(
                            ForcedType().apply {
                                name = "varchar"
                                includeExpression = ".*"
                                includeTypes = "JSONB?"
                            },
                            ForcedType().apply {
                                name = "varchar"
                                includeExpression = ".*"
                                includeTypes = "INET"
                            }
                        )
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = false
                        isImmutablePojos = false
                        isFluentSetters = false
                    }
                    target.apply {
                        packageName = "generated"
                        directory = "src/main/java/by/grsu/skydiving/adapter/out/persistence/jooq"
                    }
                    strategy.name = "org.jooq.codegen.DefaultGeneratorStrategy"
                }
            }
        }
    }
}

repositories {
    mavenCentral()
}

val jjwtVersion = "0.12.5"
val mapstructVersion = "1.5.5.Final"
val openApiVersion = "2.5.0"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${openApiVersion}")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security");
    implementation("org.mapstruct:mapstruct:${mapstructVersion}")
    implementation("io.jsonwebtoken:jjwt:${jjwtVersion}")
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework.boot:spring-boot-starter-jooq")

    compileOnly("org.projectlombok:lombok")

    runtimeOnly("org.postgresql:postgresql")

    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:${mapstructVersion}")

    jooqGenerator("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")


    liquibaseRuntime("javax.xml.bind:jaxb-api:2.3.1")
    liquibaseRuntime("org.liquibase:liquibase-core:4.16.1")
    liquibaseRuntime("org.liquibase:liquibase-groovy-dsl:3.0.2")
    liquibaseRuntime("info.picocli:picocli:4.6.1")
    liquibaseRuntime("org.postgresql:postgresql")

}

tasks.withType<Test> {
    useJUnitPlatform()
}
//
//tasks.withType<nu.studer.gradle.jooq.JooqGenerate> {
//    enabled = false
//}