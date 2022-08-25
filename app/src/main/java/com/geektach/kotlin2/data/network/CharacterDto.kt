package com.geektach.kotlin2.data.network

import java.io.Serializable

data class CharacterDto(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val gender: String? = null,
    val image: String? = null
) : Serializable