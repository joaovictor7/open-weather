package com.openweather.core.security.utils

import kotlin.random.Random

private val charPool = ('a'..'z') + ('A'..'Z') + ('0'..'9') +
    arrayOf(
        '#', '$', '&', '?', '*', '!', '+', '<', '>', '.', '§', '@', '+', '-', '=', ',', '_', '~',
        '^', '(', ')', '%'
    )

fun getAlphanumericRandomKey(textSize: Int = 100) =
    (1..textSize).map { Random.nextInt(0, charPool.size).let { charPool[it] } }
        .joinToString(String())
