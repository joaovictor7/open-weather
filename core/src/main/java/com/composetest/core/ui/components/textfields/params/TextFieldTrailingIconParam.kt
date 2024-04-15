package com.composetest.core.ui.components.textfields.params

import com.composetest.core.domain.enums.components.textfield.TextFieldIcons

data class TextFieldTrailingIconParam(
    val iconType: TextFieldIcons,
    val onClick: (() -> Unit)? = null
)