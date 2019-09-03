plugins {
    id("org.jetbrains.kotlin.jvm")
}

repositories {
    jcenter()
}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.1")
}
