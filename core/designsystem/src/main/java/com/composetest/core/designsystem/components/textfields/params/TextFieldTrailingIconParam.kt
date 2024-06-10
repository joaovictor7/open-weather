package com.composetest.core.designsystem.components.textfields.params

import com.composetest.core.designsystem.components.textfields.enums.TextFieldIcons

data class TextFieldTrailingIconParam(
    val iconType: TextFieldIcons,
    val onClick: (() -> Unit)? = null
)