package extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

private val Project.libs get() = extensions.getByType<VersionCatalogsExtension>().named("libs")
internal fun Project.getLibrary(id: String) = libs.findLibrary(id).get()
internal fun Project.findVersion(id: String) = libs.findVersion(id).get().toString()
