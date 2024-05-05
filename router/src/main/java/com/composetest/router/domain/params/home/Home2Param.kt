package com.composetest.router.domain.params.home

import com.composetest.router.domain.enums.Destination
import com.composetest.router.domain.params.base.BaseParam
import kotlinx.parcelize.Parcelize

@Parcelize
data class Home2Param(
    val teste: String
) : BaseParam {
    override val destination get() = Destination.FEATURE_HOME2
}
