import extensions.androidTestImplementation
import extensions.findLibrary
import extensions.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class TestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                androidTestImplementation(findLibrary("androidx.espresso.core"))
                androidTestImplementation(findLibrary("androidx.junit"))
                testImplementation(findLibrary("junit"))
            }
        }
    }
}