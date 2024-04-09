package com.composetest.router.destinations

import com.composetest.router.base.ScreenDestination

sealed interface LoginDestinations {
    interface Login: ScreenDestination
}