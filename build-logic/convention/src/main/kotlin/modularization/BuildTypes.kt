package modularization

import appconfig.AppBuildTypes
import appconfig.AppConfig
import appconfig.AppSignings
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

internal fun BaseAppModuleExtension.setBuildTypes() {
    buildTypes {
        AppBuildTypes.values().forEach { buildType ->
            getByName(buildType.buildTypeName) {
                AppSignings.getAssociatedBuildTypes(buildType)?.let {
                    signingConfig = signingConfigs.getByName(it.signingName)
                }
                isDebuggable = true
                setAppName(buildType)
                setBuildConfigFields(buildType)
            }
        }
    }
}

private fun ApplicationBuildType.setAppName(buildType: AppBuildTypes) = buildType.getApplicationIdSuffix(
    onRelease = {
        manifestPlaceholders["appName"] = AppConfig.APP_NAME
    },
    onNonRelease = { suffixId ->
        val buildTypeNameUpper = buildType.buildTypeName.uppercase()
        manifestPlaceholders["appName"] = "${AppConfig.APP_NAME} - $buildTypeNameUpper"
        versionNameSuffix = "-$buildTypeNameUpper"
        applicationIdSuffix = ".$suffixId"
    }
)

private fun ApplicationBuildType.setBuildConfigFields(buildType: AppBuildTypes) {
    buildConfigField("Boolean", "DYNAMIC_COLORS", "false")
    buildConfigField("Boolean", "USE_MOCK", "false")
    when (buildType) {
        AppBuildTypes.RELEASE -> {
            buildConfigField("Boolean", "DYNAMIC_COLORS", "true")
        }
        AppBuildTypes.DEBUG -> {
            buildConfigField("Boolean", "USE_MOCK", "true")
        }
        else -> Unit
    }
}