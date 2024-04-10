package com.composetest.core.utils

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.composetest.core.factories.ViewModelNavigationFactory
import com.composetest.router.providers.NavigationProvider

@Composable
inline fun <reified VM: ViewModel> hiltViewModel(navController: NavHostController) =
    hiltViewModel<VM, ViewModelNavigationFactory<VM>> {
        it.create(navController)
    }