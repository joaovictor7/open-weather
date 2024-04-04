package com.composetest.core.ui.components.textfields.commons

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import com.composetest.core.enums.textfields.TextFieldIconEnum
import com.composetest.core.ui.components.textfields.params.TextFieldTrailingIconParam

internal fun trailingIcon(
    trailingIconParam: TextFieldTrailingIconParam?,
    textValue: MutableState<String>
): @Composable (() -> Unit)? = getTrailingParam(trailingIconParam, textValue)?.let {
    {
        if (it.onClick != null) {
            IconButton(onClick = { it.onClick.invoke() }) {
                createIcon(it.iconType)?.invoke()
            }
        } else {
            createIcon(it.iconType)?.invoke()
        }
    }
}

internal fun createIcon(iconType: TextFieldIconEnum?): @Composable (() -> Unit)? = iconType?.let {
    { Icon(painter = painterResource(it.iconId), contentDescription = null) }
}

private fun getTrailingParam(
    param: TextFieldTrailingIconParam?,
    textValue: MutableState<String>,
) = when {
    param?.iconType != TextFieldIconEnum.CLEAR_TEXT -> param
    textValue.value.isNotEmpty() -> param.copy(onClick = { textValue.value = String() })
    else -> null
}