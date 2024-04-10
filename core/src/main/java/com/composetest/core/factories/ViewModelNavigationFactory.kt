package com.composetest.core.factories

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

interface ViewModelNavigationFactory<VM : ViewModel> {
    fun create(navController: NavHostController): VM
}