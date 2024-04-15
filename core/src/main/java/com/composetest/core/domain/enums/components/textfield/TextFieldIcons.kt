package com.composetest.core.domain.enums.components.textfield

import androidx.annotation.DrawableRes
import com.composetest.core.R

enum class TextFieldIcons(@DrawableRes val iconId: Int) {
    CLEAR_TEXT(R.drawable.ic_cancel),
    ERROR(R.drawable.ic_error),
    SEARCH(R.drawable.ic_search)
}