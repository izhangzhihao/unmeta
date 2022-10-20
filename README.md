# Unmeta Kotlin gradle plugin 🐘

A Gradle plugin to remove all Kotlin @Metadata and @DebugMetadata annotations from the build output.

Kotlin @Metadata and @DebugMetadata annotations are not fully processed by ProGuard / R8 and contain un-obfuscated symbol information, both in binary and plain text forms. This information can be used to more easily reverse engineer your code.

This plugin allows removing all Kotlin @Metadata / @DebugMetadata annotations from generated class files. This is safe to do as long as:

* you do not intend to use the resulting binaries as a Kotlin library (@Metadata annotations are used to determine Kotlin function definitions),
* you are not using Kotlin Reflection (certain reflection functionality depends on the presence of the @Metadata annotations).

To use the plugin add the following to your app `build.gradle.kt`:

```kotlin
plugins {
    kotlin("jvm")
    id("com.github.izhangzhihao.unmeta") version "1.0.0"
}
```


### Dependency substitution

Please note that the project relies on module name/group in order for [dependency substitution](https://docs.gradle.org/current/userguide/resolution_rules.html#sec:dependency_substitution_rules) to work properly. If you change only the plugin ID everything will work as expected. If you change module name/group, things might break and you probably have to specify a [substitution rule](https://docs.gradle.org/current/userguide/resolution_rules.html#sub:project_to_module_substitution).


## Contributing 🤝

Feel free to open a issue or submit a pull request for any bugs/improvements.

## License 📄

This template is licensed under the MIT License - see the [License](License) file for details.
Please note that the generated template is offering to start with a MIT license but you can change it to whatever you wish, as long as you attribute under the MIT terms that you're using the template.
