import java.util.Properties
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

// Phiên bản lấy từ version.properties (nguồn duy nhất) — xem docs/decisions/0002-version-scheme.md
val versionProps = Properties().apply {
    rootProject.file("version.properties").inputStream().use { load(it) }
}

// Ký release: chỉ bật khi CI/máy dev cung cấp keystore qua biến môi trường. KHÔNG commit keystore.
fun env(name: String): String? = System.getenv(name)?.takeIf { it.isNotBlank() }
val releaseKeystore: File? = env("LO_KEYSTORE_FILE")?.let { File(it) }?.takeIf { it.exists() }

android {
    namespace = "com.lastoutpost.game"
    compileSdk = 36

    defaultConfig {
        // GIẢ ĐỊNH — cần xác nhận trước khi phát hành lên Play (OQ-TECH-02): applicationId khó đổi sau khi publish.
        applicationId = "com.lastoutpost.game"
        minSdk = 26 // GIẢ ĐỊNH (OQ-TECH-05)
        targetSdk = 36 // Google Play yêu cầu API 36 cho app mới từ 31/08/2026

        versionCode = versionProps.getProperty("VERSION_CODE").toInt()
        versionName = versionProps.getProperty("VERSION_NAME")

        buildConfigField("String", "BUILD_LABEL", "\"${versionProps.getProperty("BUILD_LABEL")}\"")
        buildConfigField("String", "RELEASE_CHANNEL", "\"${versionProps.getProperty("RELEASE_CHANNEL")}\"")
    }

    signingConfigs {
        if (releaseKeystore != null) {
            create("release") {
                storeFile = releaseKeystore
                storePassword = env("LO_KEYSTORE_PASSWORD")
                keyAlias = env("LO_KEY_ALIAS")
                keyPassword = env("LO_KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        release {
            // R8 tắt ở beta-1 (chưa kiểm chứng trên thiết bị) — xem docs/production/TECH_DEBT.md (TD-03).
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfigs.findByName("release")?.let { signingConfig = it }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    // Content data-driven dùng chung cho validator (CI) và app: đóng gói content/ vào assets.
    sourceSets.getByName("main").assets.srcDir(rootProject.file("content"))
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    implementation(project(":core"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    debugImplementation(libs.androidx.compose.ui.tooling)
}
