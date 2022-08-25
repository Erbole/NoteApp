package com.geektach.kotlin2.data.network

data class MainResultDto<T>(
    val info: InfoDto? = null,
    val result: List<T>
)