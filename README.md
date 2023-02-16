# Unmeta Kotlin gradle plugin 🐘

A Gradle plugin to remove all Kotlin @Metadata and @DebugMetadata annotations from the compile output and jars.

Kotlin @Metadata and @DebugMetadata annotations are not fully processed by ProGuard / R8 and contain un-obfuscated symbol information, both in binary and plain text forms. This information can be used to more easily reverse engineer your code.

This plugin allows removing all Kotlin @Metadata / @DebugMetadata annotations from generated class files. This is safe to do as long as:

* you do not intend to use the resulting binaries as a Kotlin library (@Metadata annotations are used to determine Kotlin function definitions),
* you are not using Kotlin Reflection (certain reflection functionality depends on the presence of the @Metadata annotations).

## By the Kotlin dsl in `build.gradle.kt`

```kotlin
plugins {
    kotlin("jvm")
    id("io.github.izhangzhihao.unmeta") version "1.0.0"
}

unmeta {
    enable.set(true)
}
```

To enable the plugin only for release builds add this section:

```kotlin
gradle.taskGraph.whenReady {
    if (allTasks.any { it.name.contains("release") }) {
        unmeta {
            enable.set(true)
        }
    } else {
        unmeta {
            enable.set(false)
        }
    }
}
```

## By using the Groovy dsl in `build.gradle`

```kotlin
plugins {
    id 'org.jetbrains.kotlin.jvm' version '1.8.10'
    id "io.github.izhangzhihao.unmeta" version "1.0.0"
}

unmeta {
    enable.set(true)
}
```

To enable the plugin only for release builds add this section:

```groovy
gradle.taskGraph.whenReady { graph ->
    if (graph.getAllTasks().any { it.name.contains("release") }) {
        unmeta {
            enable.set(true)
        }
    } else {
        unmeta {
            enable.set(false)
        }
    }
}
```

## Verify

```
./gradlew clean jar --dry-run
:clean SKIPPED
:compileKotlin SKIPPED
:unmeta SKIPPED
:compileJava SKIPPED
:processResources SKIPPED
:classes SKIPPED
:jar SKIPPED
```

You might need this to force the `unmeta` task runs before the `jar` task:

```kotlin
jar {
        mustRunAfter("unmeta")
}
```

## Dependency substitution

Please note that the project relies on module name/group in order for [dependency substitution](https://docs.gradle.org/current/userguide/resolution_rules.html#sec:dependency_substitution_rules) to work properly. If you change only the plugin ID everything will work as expected. If you change module name/group, things might break and you probably have to specify a [substitution rule](https://docs.gradle.org/current/userguide/resolution_rules.html#sub:project_to_module_substitution).


## Contributing 🤝

Feel free to open a issue or submit a pull request for any bugs/improvements.

## License 📄

This template is licensed under the MIT License - see the [License](License) file for details.
