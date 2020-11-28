// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.build.gradle.kts files
    }
    //apply("version.gradle.kts")
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task("clean", Delete::class) {
    delete(rootProject.buildDir)
}