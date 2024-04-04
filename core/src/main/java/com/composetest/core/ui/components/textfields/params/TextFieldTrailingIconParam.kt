package com.composetest.core.ui.components.textfields.params

import com.composetest.core.enums.textfields.TextFieldIconEnum

data class TextFieldTrailingIconParam(
    val iconType: TextFieldIconEnum,
    val onClick: (() -> Unit)? = null
)