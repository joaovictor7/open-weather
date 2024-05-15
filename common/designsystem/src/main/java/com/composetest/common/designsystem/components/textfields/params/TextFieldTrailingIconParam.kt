package com.composetest.common.designsystem.components.textfields.params

import com.composetest.core.ui.domain.enums.textfield.TextFieldIcons

data class TextFieldTrailingIconParam(
    val iconType: TextFieldIcons,
    val onClick: (() -> Unit)? = null
)