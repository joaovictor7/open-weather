import com.android.build.gradle.BaseExtension
import extensions.androidTestImplementation
import extensions.debugImplementation
import extensions.findLibrary
import extensions.findVersion
import extensions.implementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

internal class ComposeConventionPlugin : Plugin<Project> {
    @Suppress("UnstableApiUsage")
    override fun apply(target: Project) {
        with(target) {
            extensions.configure<BaseExtension> {
                buildFeatures.compose = true
                composeOptions {
                    kotlinCompilerExtensionVersion = findVersion("kotlinComposeCompilerExtension")
                }
                dependencies {
                    val composeBom = platform(findLibrary("compose.bom"))
                    implementation(composeBom)
                    implementation(findLibrary("compose.activity"))
                    implementation(findLibrary("compose.ui"))
                    implementation(findLibrary("compose.ui.graphics"))
                    implementation(findLibrary("compose.ui.tooling.preview"))
                    implementation(findLibrary("compose.material3"))
                    androidTestImplementation(composeBom)
                    debugImplementation(findLibrary("compose.ui.test.manifest"))
                    androidTestImplementation(findLibrary("compose.ui.test.junit4"))
                    debugImplementation(findLibrary("compose.ui.tooling"))
                }
            }
        }
    }
}