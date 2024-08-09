package io.github.izhangzhihao.unmeta

import org.gradle.api.Plugin
import org.gradle.api.Project

class UnmetaPlugin : Plugin<Project> {

    private lateinit var extension: UnmetaExtension

    override fun apply(project: Project) {
        extension = project.extensions.create("unmeta", UnmetaExtension::class.java, project)
        val unmetaTaskTask = project.tasks.create("unmeta", UnmetaTask::class.java)
        unmetaTaskTask.enable.set(extension.enable)
        val t = try {
            project.tasks.getByName("compileKotlin")
        } catch (e: org.gradle.api.UnknownTaskException) {
            project.tasks.getByName("compileKotlinJvm")
        }
        t.finalizedBy(unmetaTaskTask)
    }
}
