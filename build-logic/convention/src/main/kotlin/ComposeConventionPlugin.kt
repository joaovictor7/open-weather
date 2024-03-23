import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

internal class ComposeConventionPlugin : Plugin<Project> {
    @Suppress("UnstableApiUsage")
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<BaseExtension> {
                val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
                buildFeatures.compose = true
                composeOptions {
                    kotlinCompilerExtensionVersion = libs.findVersion("kotlinComposeCompilerExtension").get().toString()
                }
                dependencies {
                    add("implementation", platform(libs.findLibrary("androidx.compose.bom").get()))
                    add("implementation", libs.findLibrary("androidx.compose.activity").get())
                    add("implementation", libs.findLibrary("androidx.compose.ui").get())
                    add("implementation", libs.findLibrary("androidx.compose.ui.graphics").get())
                    add("implementation", libs.findLibrary("androidx.compose.ui.tooling.preview").get())
                    add("implementation", libs.findLibrary("androidx.compose.material3").get())
                    add("debugImplementation", libs.findLibrary("androidx.compose.ui.tooling").get())
                }
            }
        }
    }
}