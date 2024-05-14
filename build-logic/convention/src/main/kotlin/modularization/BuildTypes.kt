package modularization

import appconfig.AppBuildTypes
import appconfig.AppConfig
import appconfig.AppSignings
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun BaseAppModuleExtension.setApplicationBuildTypes() {
    buildTypes {
        AppBuildTypes.values().forEach { buildType ->
            getByName(buildType.buildTypeName) {
                setSigning(buildType, this@setApplicationBuildTypes)
                setAppName(buildType)
                setBuildConfigFields(buildType)
            }
        }
    }
}

internal fun Project.setBuildTypesAllModules() = extensions.configure<BaseExtension> {
    AppBuildTypes.values().forEach { buildType ->
        if (buildType.isInternal) {
            setInternalBuildTypes(buildType)
        } else {
            createNewBuildTypes(buildType)
        }
    }
}

private fun BaseExtension.setInternalBuildTypes(buildType: AppBuildTypes) = buildTypes {
    getByName(buildType.buildTypeName) {
        isDefault = buildType.isDefault
        isDebuggable = buildType.isDebuggable
        when (buildType) {
            AppBuildTypes.RELEASE -> {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
            else -> Unit
        }
    }
}

private fun BaseExtension.createNewBuildTypes(buildType: AppBuildTypes) = buildTypes {
    create(buildType.buildTypeName) {
        isDefault = buildType.isDefault
        isDebuggable = buildType.isDebuggable
    }
}

private fun ApplicationBuildType.setSigning(
    buildType: AppBuildTypes,
    baseAppModuleExtension: BaseAppModuleExtension
) = with(baseAppModuleExtension) {
    AppSignings.getAssociatedBuildType(buildType)?.let { appSigning ->
        signingConfigs.find { signingConfig ->
            signingConfig.name == appSigning.signingName
        }?.let { signing -> signingConfig = signing }
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