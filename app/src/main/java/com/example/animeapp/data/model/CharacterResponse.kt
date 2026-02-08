package com.example.animeapp.data.model

data class CharacterResponse(
    val data: List<CharacterData>
)

data class CharacterData(
    val character: Character,
    val role: String
)

data class Character(
    val name: String
)

