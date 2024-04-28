package com.composetest.router.domain.params.home

import com.composetest.router.domain.enums.Destination
import com.composetest.router.domain.params.base.BaseParam
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeParam(
    val teste: String,
    override val destination: Destination = Destination.FEATURE_HOME
) : BaseParam
