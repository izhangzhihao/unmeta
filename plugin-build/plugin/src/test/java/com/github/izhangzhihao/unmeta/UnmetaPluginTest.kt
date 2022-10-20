//package com.github.izhangzhihao.unmeta
//
//import org.gradle.testfixtures.ProjectBuilder
//import org.junit.Assert.assertEquals
//import org.junit.Assert.assertNotNull
//import org.junit.Test
//
//class UnmetaPluginTest {
//
//    @Test
//    fun `plugin is applied correctly to the project`() {
//        val project = ProjectBuilder.builder().build()
//        project.pluginManager.apply("com.github.izhangzhihao.unmeta")
//
//        assert(project.tasks.getByName("unmeta") is UnmetaTask)
//    }
//
//    @Test
//    fun `extension templateExampleConfig is created correctly`() {
//        val project = ProjectBuilder.builder().build()
//        project.pluginManager.apply("com.github.izhangzhihao.unmeta")
//
//        assertNotNull(project.extensions.getByName("unmeta"))
//    }
//
//    @Test
//    fun `parameters are passed correctly from extension to task`() {
//        val project = ProjectBuilder.builder().build()
//        project.pluginManager.apply("com.github.izhangzhihao.unmeta")
//        (project.extensions.getByName("unmeta") as UnmetaExtension).apply {
//            enable = true
//        }
//
//        val task = project.tasks.getByName("unmeta") as UnmetaTask
//
//        assertEquals(true, task.enable)
//    }
//}
