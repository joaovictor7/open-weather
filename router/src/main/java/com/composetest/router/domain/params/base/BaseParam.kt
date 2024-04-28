package com.composetest.router.domain.params.base

import android.os.Parcelable
import com.composetest.router.domain.enums.Destination

interface BaseParam : Parcelable {
    val destination: Destination
}