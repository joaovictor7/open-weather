package com.composetest.feature.login.ui

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginParam(
    val teste: String = String()
): Parcelable