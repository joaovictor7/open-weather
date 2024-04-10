package com.composetest.core.models

data class BuildConfigModel(
    val applicationId: String,
    val versionName: String,
    val versionCode: Int,
    val buildType: String,
    val flavor: String,
    val dynamicColors: Boolean,
    val useMock: Boolean
) {
    val versionNameWithVersionCode get() = "$versionName - $versionCode"
}