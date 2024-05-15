package com.composetest.common.utility.domain.models

data class BuildConfigModel(
    val applicationId: String,
    val versionName: String,
    val versionCode: Int,
    val buildType: String,
    val flavor: String,
    val dynamicColors: Boolean,
    val useMock: Boolean
) {
    val versionNameForView: String
        get() {
            val flavor = if (buildType != RELEASE_BUILD_TYPE) " ($flavor)" else String()
            return "$versionName - $versionCode$flavor"
        }

    private companion object {
        const val RELEASE_BUILD_TYPE = "release"
    }
}