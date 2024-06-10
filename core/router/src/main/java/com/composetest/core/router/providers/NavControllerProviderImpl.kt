package com.composetest.core.router.providers

import androidx.navigation.NavHostController
import com.composetest.common.providers.NavControllerProvider
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NavControllerProviderImpl @Inject constructor() : NavControllerProvider {

    override var navController: NavHostController? = null
        private set

    override fun setNavController(navController: NavHostController) {
        this.navController = navController
    }
}