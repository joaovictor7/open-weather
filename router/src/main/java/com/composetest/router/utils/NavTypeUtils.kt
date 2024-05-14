package com.composetest.router.utils

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.composetest.core.extensions.parcelable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KType
import kotlin.reflect.typeOf

inline fun <reified T : Parcelable> navType(
    isNullableAllowed: Boolean = false
): Pair<KType, NavType<T>> =
    typeOf<T>() to object : NavType<T>(isNullableAllowed = isNullableAllowed) {
        override fun get(bundle: Bundle, key: String): T? = bundle.parcelable(key)

        override fun parseValue(value: String): T = Json.decodeFromString<T>(value)

        override fun serializeAsValue(value: T) = Json.encodeToString(value)

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }
    }


inline fun <reified T> navTypeSerialized( // testing in next navigation update
    isNullableAllowed: Boolean = false
): Pair<KType, NavType<T>> =
    typeOf<T>() to object : NavType<T>(isNullableAllowed = isNullableAllowed) {
        override fun get(bundle: Bundle, key: String): T? =
            bundle.getString(key)?.let { Json.decodeFromString<T>(it) }

        override fun parseValue(value: String) = Json.decodeFromString<T>(value)

        override fun serializeAsValue(value: T): String = Json.encodeToString(value)

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }