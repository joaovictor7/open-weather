import extensions.debugImplementation
import extensions.findLibrary
import extensions.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal class ComposeConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            dependencies {
                implementation(platform(findLibrary("compose.bom")))
                implementation(findLibrary("compose.activity"))
                implementation(findLibrary("compose.ui.tooling.preview"))
                implementation(findLibrary("compose.material3"))
                implementation(findLibrary("compose.navigation"))
                implementation(findLibrary("androidx.lifecycle.runtime.compose"))
                implementation(findLibrary("androidx.hilt.navigation.compose"))
                debugImplementation(findLibrary("compose.ui.tooling"))
            }
        }
    }
}