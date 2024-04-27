package com.composetest.core.ui.components.textfields.utils

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.painterResource
import com.composetest.core.ui.R
import com.composetest.core.ui.components.textfields.params.TextFieldTrailingIconParam
import com.composetest.core.ui.domain.enums.textfield.TextFieldIcons

internal fun trailingIcon(
    trailingIconParam: TextFieldTrailingIconParam?,
    textValue: MutableState<String>,
    password: Boolean,
    passwordHidden: MutableState<Boolean>
): @Composable (() -> Unit)? = getTrailingParam(trailingIconParam, textValue)?.let {
    {
        when {
            password -> {
                IconButton(onClick = { passwordHidden.value = !passwordHidden.value }) {
                    createIcon(if (passwordHidden.value) R.drawable.ic_visibility else R.drawable.ic_visibility_off)?.invoke()
                }
            }
            it.onClick != null -> {
                IconButton(onClick = { it.onClick.invoke() }) {
                    createIcon(it.iconType.iconId)?.invoke()
                }
            }
            else -> {
                createIcon(it.iconType.iconId)?.invoke()
            }
        }
    }
}

internal fun createIcon(@DrawableRes iconId: Int?): @Composable (() -> Unit)? = iconId?.let {
    { Icon(painter = painterResource(it), contentDescription = null) }
}

private fun getTrailingParam(
    param: TextFieldTrailingIconParam?,
    textValue: MutableState<String>
) = when {
    param?.iconType != TextFieldIcons.CLEAR_TEXT -> param
    textValue.value.isNotEmpty() -> param.copy(onClick = { textValue.value = String() })
    else -> null
}