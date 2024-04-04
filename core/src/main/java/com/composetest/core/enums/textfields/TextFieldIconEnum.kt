package com.composetest.core.enums.textfields

import androidx.annotation.DrawableRes
import com.composetest.core.R

enum class TextFieldIconEnum(@DrawableRes val iconId: Int) {
    CLEAR_TEXT(R.drawable.ic_cancel),
    ERROR(R.drawable.ic_error),
    SEARCH(R.drawable.ic_search)
}