// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.android.build.gradle.BaseExtension

buildscript {
    val kotlinVersion by extra("1.4.21")
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
        maven("https://dl.bintray.com/chaozhouzhang/maven")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
        maven("https://dl.bintray.com/chaozhouzhang/maven")
    }
}


val Project.android get() = extensions.getByName<BaseExtension>("android")

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.library") ||
            plugins.hasPlugin("com.android.application")) {
            android.apply {
                compileSdkVersion(30)
                buildToolsVersion = "30.0.3"

                defaultConfig {
                    if (minSdkVersion == null)
                        minSdkVersion(21)
                    targetSdkVersion(30)
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_1_8
                    targetCompatibility = JavaVersion.VERSION_1_8
                }
            }
        }
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}