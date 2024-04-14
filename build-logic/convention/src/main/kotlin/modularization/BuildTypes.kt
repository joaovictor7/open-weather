package modularization

import appconfig.AppBuildTypes
import appconfig.AppConfig
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

internal fun BaseAppModuleExtension.setBuildTypes() {
    buildTypes {
        AppBuildTypes.values().forEach { buildType ->
            getByName(buildType.buildTypeName) {
                signingConfig = when (buildType) {
                    AppBuildTypes.RELEASE -> signingConfigs.getByName(buildType.buildTypeName)
                    else -> signingConfigs.getByName(AppBuildTypes.DEBUG.buildTypeName)
                }
                isDebuggable = true
                setAppName(buildType)
                setBuildConfigFields(buildType)
            }
        }
    }
}

private fun ApplicationBuildType.setAppName(buildType: AppBuildTypes) = with(buildType) {
    getApplicationIdSuffix(
        onRelease = {
            manifestPlaceholders["appName"] = AppConfig.APP_NAME
        },
        onNonRelease = { idSuffix ->
            val buildTypeNameUpper = buildType.buildTypeName.uppercase()
            manifestPlaceholders["appName"] = "${AppConfig.APP_NAME} - $buildTypeNameUpper"
            versionNameSuffix = "-$buildTypeNameUpper"
            applicationIdSuffix = ".$idSuffix"
        }
    )
}

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