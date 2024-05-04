package com.composetest.router.utils

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.composetest.core.extensions.parcelable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

inline fun <reified T : Parcelable> parcelableNavType(isNullableAllowed: Boolean = false) =
    object : NavType<T>(isNullableAllowed = isNullableAllowed) {
        override fun get(bundle: Bundle, key: String): T? = bundle.parcelable(key)

        override fun parseValue(value: String): T = Json.decodeFromString<T>(value)

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }

        override fun serializeAsValue(value: T) = Json.encodeToString(value)
    }
