// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion by extra("1.4.21")

    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
        maven("https://dl.bintray.com/chaozhouzhang/maven")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.build.gradle.kts files
    }
    //apply("version.gradle.kts")
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://jitpack.io")
        maven("https://dl.bintray.com/chaozhouzhang/maven")
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}