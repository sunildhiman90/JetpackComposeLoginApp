plugins {
    kotlin("multiplatform")
    id("com.android.library")

    id("org.jetbrains.compose") version "1.5.11"
}

kotlin {

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)

                implementation("dev.icerock.moko:mvvm-core:0.16.1")

            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting

        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

android {
    namespace = "com.codingambitions.loginapp.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
}