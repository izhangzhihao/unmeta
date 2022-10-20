package io.github.izhangzhihao.unmeta

import org.gradle.api.Project
import org.gradle.api.provider.Property
import javax.inject.Inject

open class UnmetaExtension @Inject constructor(project: Project) {
    private val objects = project.objects

    val enable: Property<Boolean> = objects.property(Boolean::class.java)
}
