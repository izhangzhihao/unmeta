package io.github.izhangzhihao.unmeta

import org.gradle.api.logging.Logger
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes

class UnmetaClassVisitor(private val path: String, cv: ClassVisitor, private val logger: Logger) :
    ClassVisitor(Opcodes.ASM9, cv), Opcodes {

    var modified = false

    override fun visitAnnotation(desc: String?, visible: Boolean): AnnotationVisitor? {
        return when (desc) {
            "Lkotlin/Metadata;" -> {
                logger.debug("Removed @Metadata annotation from $path")
                modified = true
                null
            }
            "Lkotlin/coroutines/jvm/internal/DebugMetadata;" -> {
                logger.debug("Removed @DebugMetadata annotation from $path")
                modified = true
                null
            }
            "Lkotlin/jvm/internal/SourceDebugExtension;" -> {
                logger.debug("Removed @SourceDebugExtension annotation from $path")
                modified = true
                null
            }
            else -> {
                super.visitAnnotation(desc, visible)
            }
        }
    }
}
