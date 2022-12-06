package com.bagaspardanailham.pokedexapp.data.remote

import okhttp3.ResponseBody

sealed class Result<out R> private constructor() {
    data class Success<out T>(val data: T): Result<T>()
    data class Error(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
        ): Result<Nothing>()
    object Loading : Result<Nothing>()
}