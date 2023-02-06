package com.my.sapiaassignment.datalayer.repository.petslist

import com.my.sapiaassignment.base.network.DataResult
import com.my.sapiaassignment.datalayer.ApiService
import com.my.sapiaassignment.datalayer.apimodel.PetItem
import com.my.sapiaassignment.datalayer.apimodel.PetsResponse
import javax.inject.Inject

class PetsRepositoryImpl @Inject constructor(private val retrofitService: ApiService) :
    PetsRepository {

    override suspend fun fetchPetsData(): DataResult<List<PetItem>?> {
        return try {
            val call = retrofitService.getPetsList()
            val data: PetsResponse? = call.body()
            DataResult.Success(data?.pets ?: emptyList())
        } catch (e: Exception) {
            DataResult.Error(e)
        }
    }

}