package com.krachkovsky.mycurrentip.api

data class Source(
    val ip: String,
    val xForwardedFor: String
)
