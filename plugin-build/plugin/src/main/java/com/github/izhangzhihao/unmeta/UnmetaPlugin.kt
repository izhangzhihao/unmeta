package com.github.izhangzhihao.unmeta

import org.gradle.api.Plugin
import org.gradle.api.Project

class UnmetaPlugin : Plugin<Project> {

    private lateinit var extension: UnmetaExtension

    override fun apply(project: Project) {
        extension = project.extensions.create("unmeta", UnmetaExtension::class.java)

        val unmetaTaskTask = project.tasks.create("unmeta", UnmetaTask::class.java)

        project.tasks.getByName("compileKotlin").finalizedBy(unmetaTaskTask)
        unmetaTaskTask.enable = extension.enable
    }
}
