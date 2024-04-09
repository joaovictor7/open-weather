package com.composetest.router.params.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeParam(
    val teste: String
) : Parcelable
