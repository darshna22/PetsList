package com.my.sapiaassignment.datalayer.repository.petslist

import com.my.sapiaassignment.base.network.DataResult
import com.my.sapiaassignment.datalayer.apimodel.PetItem

interface PetsRepository {
    suspend fun fetchPetsData(): DataResult<List<PetItem>?>
}