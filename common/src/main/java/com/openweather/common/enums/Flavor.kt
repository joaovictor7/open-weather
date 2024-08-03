package com.openweather.common.enums

enum class Flavor {
    FULL, FREE;

    val flavorName get() = name.lowercase()

    companion object {
        fun String.getFlavor() = entries.firstOrNull { it.flavorName == this } ?: FREE
    }
}