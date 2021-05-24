package com.example.rick_n_morty.api

import com.squareup.moshi.Json

data class Response(
    @Json(name = "name")
    val characterName: String,

    @Json(name = "status")
    val characterStatus: String,

    @Json(name = "species")
    val characterSpecies: String,

    @Json(name = "image")
    val characterImage: String,
)

data class CharacterResponse(val results: List<Response>)