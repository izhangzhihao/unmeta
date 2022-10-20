package io.github.izhangzhihao.unmeta

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import java.io.File

abstract class UnmetaTask : DefaultTask() {

    init {
        description = "Drop @Metadata & @DebugMetadata from kotlin classes"
        group = "build"
    }

    @get:Input
    @get:Option(option = "enable", description = "is unmeta enabled")
    @get:Optional
    abstract val enable: Property<Boolean>

    @TaskAction
    fun unmetaAction() {
        if (!enable.get()) {
            logger.warn("unmeta is disabled")
        } else {
            logger.info("Start drop @Metadata & @DebugMetadata from kotlin classes")
            project.buildDir.listFiles()
                ?.forEach { file ->
                    if (file.isDirectory) {
                        dropMetadata(file)
                    }
                }
        }
    }

    private fun dropMetadata(file: File) {
        file.walk()
            .filter { it.path.contains("classes") && it.path.endsWith(".class") }
            .forEach {
                if (it.isFile) {
                    val sourceClassBytes = it.readBytes()
                    val classReader = ClassReader(sourceClassBytes)
                    val classWriter = ClassWriter(classReader, ClassWriter.COMPUTE_MAXS)
                    val unmetaClassVisitor = UnmetaClassVisitor(it.absolutePath, classWriter, logger)
                    classReader.accept(unmetaClassVisitor, ClassReader.SKIP_DEBUG)
                    if (unmetaClassVisitor.modified) {
                        it.writeBytes(classWriter.toByteArray())
                    }
                }
            }
    }
}
