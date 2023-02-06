package com.my.sapiaassignment.datalayer

import com.my.sapiaassignment.datalayer.apimodel.PetsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("f6f16b70-1b49-4805-9115-19d040154aae")
    suspend fun getPetsList(): Response<PetsResponse>

}