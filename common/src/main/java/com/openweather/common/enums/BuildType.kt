package com.openweather.common.enums

enum class BuildType {
    RELEASE, STAGING, DEBUG;

    val buildTypeName get() = name.lowercase()

    companion object {
        fun String.getBuildType() = entries.firstOrNull { it.buildTypeName == this } ?: DEBUG
    }
}