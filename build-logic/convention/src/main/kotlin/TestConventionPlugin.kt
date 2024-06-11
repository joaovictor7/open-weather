import extensions.getLibrary
import extensions.implementation
import extensions.testImplementation
import extensions.testRuntimeOnly
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

internal class TestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            tasks.withType<Test> {
                useJUnitPlatform()
            }
            dependencies {
                implementation(project(":core:test"))
                testImplementation(getLibrary("junit5"))
                testImplementation(getLibrary("mockk"))
                testRuntimeOnly(getLibrary("junit5.engine"))
            }
        }
    }
}