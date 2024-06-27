package com.composetest.common.models

data class BuildConfigFieldsModel(
    val applicationId: String,
    val versionName: String,
    val versionCode: Int,
    val buildType: String,
    val flavor: String,
    val androidSdkVersion: Int
) {

    val isDebug = DEBUG_BUILD_TYPE == buildType
    val isStaging = STAGING_BUILD_TYPE == buildType
    val isRelease = RELEASE_BUILD_TYPE == buildType

    val versionNameForView: String
        get() {
            val flavor = if (!isRelease) " ($flavor)" else String()
            return "$versionName - $versionCode$flavor"
        }

    private companion object {
        const val RELEASE_BUILD_TYPE = "release"
        const val DEBUG_BUILD_TYPE = "debug"
        const val STAGING_BUILD_TYPE = "staging"
    }
}