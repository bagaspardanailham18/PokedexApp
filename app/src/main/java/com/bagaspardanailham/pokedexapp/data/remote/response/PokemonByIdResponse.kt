package com.bagaspardanailham.pokedexapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class PokemonByIdResponse(

	@field:SerializedName("location_area_encounters")
	val locationAreaEncounters: String? = null,

	@field:SerializedName("types")
	val types: List<Any?>? = null,

	@field:SerializedName("base_experience")
	val baseExperience: Int? = null,

	@field:SerializedName("held_items")
	val heldItems: List<Any?>? = null,

	@field:SerializedName("weight")
	val weight: Int? = null,

	@field:SerializedName("is_default")
	val isDefault: Boolean? = null,

	@field:SerializedName("past_types")
	val pastTypes: List<Any?>? = null,

	@field:SerializedName("sprites")
	val sprites: Sprites? = null,

	@field:SerializedName("abilities")
	val abilities: List<Any?>? = null,

	@field:SerializedName("game_indices")
	val gameIndices: List<Any?>? = null,

	@field:SerializedName("species")
	val species: Species? = null,

	@field:SerializedName("stats")
	val stats: List<StatsItem?>? = null,

	@field:SerializedName("moves")
	val moves: List<Any?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("forms")
	val forms: List<Any?>? = null,

	@field:SerializedName("height")
	val height: Int? = null,

	@field:SerializedName("order")
	val order: Int? = null
)

data class DreamWorld(

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: Any? = null
)

data class Other(

	@field:SerializedName("dream_world")
	val dreamWorld: DreamWorld? = null,

	@field:SerializedName("official-artwork")
	val officialArtwork: OfficialArtwork? = null,

	@field:SerializedName("home")
	val home: Home? = null
)

data class Species(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class OfficialArtwork(

	@field:SerializedName("front_default")
	val frontDefault: String? = null
)

data class Home(

	@field:SerializedName("front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("front_female")
	val frontFemale: Any? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class Sprites(

	@field:SerializedName("back_shiny_female")
	val backShinyFemale: Any? = null,

	@field:SerializedName("back_female")
	val backFemale: Any? = null,

	@field:SerializedName("other")
	val other: Other? = null,

	@field:SerializedName("back_default")
	val backDefault: String? = null,

	@field:SerializedName("front_shiny_female")
	val frontShinyFemale: Any? = null,

	@field:SerializedName("front_default")
	val frontDefault: String? = null,

	@field:SerializedName("versions")
	val versions: Versions? = null,

	@field:SerializedName("front_female")
	val frontFemale: Any? = null,

	@field:SerializedName("back_shiny")
	val backShiny: String? = null,

	@field:SerializedName("front_shiny")
	val frontShiny: String? = null
)

data class Stat(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class StatsItem(

	@field:SerializedName("stat")
	val stat: Stat? = null,

	@field:SerializedName("base_stat")
	val baseStat: Int? = null,

	@field:SerializedName("effort")
	val effort: Int? = null
)

data class Versions(
	val any: Any? = null
)
