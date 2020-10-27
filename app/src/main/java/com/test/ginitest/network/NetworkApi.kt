package com.test.ginitest.network

import retrofit2.http.GET

interface NetworkApi {
    @GET("raw/8wJzytQX")
    suspend fun getNumbers(): NumberResponse
}