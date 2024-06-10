package com.composetest.core.designsystem.components.extensions.modifiers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Composable
fun Modifier.componentVisibility(isVisible: Boolean) = alpha(if (isVisible) 1f else 0f)