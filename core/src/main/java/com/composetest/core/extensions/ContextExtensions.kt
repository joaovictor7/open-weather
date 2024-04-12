package com.composetest.core.extensions

import android.app.Activity
import android.content.Context

val Context.activity get() = this as Activity