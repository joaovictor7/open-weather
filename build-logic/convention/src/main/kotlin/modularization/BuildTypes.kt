package modularization

import appconfig.AppBuildType
import appconfig.AppConfig
import appconfig.AppSigning
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun BaseAppModuleExtension.setApplicationBuildTypes() {
    buildTypes {
        AppBuildType.values().forEach { buildType ->
            getByName(buildType.buildTypeName) {
                setSigning(buildType, this@setApplicationBuildTypes)
                setAppName(buildType)
                setBuildConfigFields(buildType)
            }
        }
    }
}

internal fun Project.setBuildTypesAllModules() = extensions.configure<BaseExtension> {
    AppBuildType.values().forEach { buildType ->
        if (buildType.isInternal) {
            setInternalBuildTypes(buildType)
        } else {
            createNewBuildTypes(buildType)
        }
    }
}

private fun BaseExtension.setInternalBuildTypes(buildType: AppBuildType) = buildTypes {
    getByName(buildType.buildTypeName) {
        isDefault = buildType.isDefault
        isDebuggable = buildType.isDebuggable
        when (buildType) {
            AppBuildType.RELEASE -> {
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

private fun BaseExtension.createNewBuildTypes(buildType: AppBuildType) = buildTypes {
    create(buildType.buildTypeName) {
        isDefault = buildType.isDefault
        isDebuggable = buildType.isDebuggable
    }
}

private fun ApplicationBuildType.setSigning(
    buildType: AppBuildType,
    baseAppModuleExtension: BaseAppModuleExtension
) = with(baseAppModuleExtension) {
    AppSigning.getAssociatedBuildType(buildType)?.let { appSigning ->
        signingConfigs.find { signingConfig ->
            signingConfig.name == appSigning.signingName
        }?.let { signing -> signingConfig = signing }
    }
}

private fun ApplicationBuildType.setAppName(buildType: AppBuildType) = buildType.getApplicationIdSuffix(
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