package com.composetest.core.router.results

import com.composetest.core.router.interfaces.Result
import kotlinx.parcelize.Parcelize

@Parcelize
data class Home2Result(
    val e: String
) : Result
