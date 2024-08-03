package com.openweather.core.designsystem.components.textfields.enums

import androidx.annotation.DrawableRes
import com.openweather.core.designsystem.R

enum class TextFieldIcons(@DrawableRes val iconId: Int) {
    CLEAR_TEXT(R.drawable.ic_cancel),
    ERROR(R.drawable.ic_error),
    SEARCH(R.drawable.ic_search)
}