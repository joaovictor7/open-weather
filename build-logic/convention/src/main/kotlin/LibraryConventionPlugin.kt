import modularization.configureAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project

internal class LibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
            }
            configureAndroid()
        }
    }
}