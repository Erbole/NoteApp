package com.geektech.homework71.domain.model.network

data class MainResult<T>(
    val info: Info? = null,
    val result: List<T>
)
