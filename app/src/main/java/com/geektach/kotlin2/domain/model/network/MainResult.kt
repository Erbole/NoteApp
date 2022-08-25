package com.geektach.kotlin2.domain.model.network

data class MainResult<T>(
    val info: Info? = null,
    val result: List<T>
)
