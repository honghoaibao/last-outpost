import org.jetbrains.kotlin.gradle.dsl.JvmTarget

// :core — mô phỏng gameplay THUẦN Kotlin/JVM, không phụ thuộc Android/UI/engine.
// Lý do: Tech Architecture §XXXIV.1 "Gameplay simulation không phụ thuộc UI" → test được trên CI mà không cần emulator,
// và có thể thay lớp render/engine (docs/decisions/0001) mà không viết lại logic.
plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = false
    }
}
