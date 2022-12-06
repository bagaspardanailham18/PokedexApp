package com.bagaspardanailham.pokedexapp.utils

fun String.extractId() = this.substringAfter("pokemon").replace("/", "").toInt()

fun String.getPictUrl(): String {
    var id = this.extractId()
    return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
}