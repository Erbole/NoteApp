package com.geektach.kotlin2.domain.model.network

import java.io.Serializable

data class Character(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val gender: String? = null,
    val image: String? = null
) : Serializable