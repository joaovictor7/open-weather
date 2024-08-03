package com.openweather.core.designsystem.components.textfields.params

import com.openweather.core.designsystem.components.textfields.enums.TextFieldIcons

data class TextFieldTrailingIconParam(
    val iconType: TextFieldIcons,
    val onClick: (() -> Unit)? = null
)