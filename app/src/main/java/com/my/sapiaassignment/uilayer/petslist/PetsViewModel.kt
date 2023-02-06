package com.my.sapiaassignment.uilayer.petslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.my.sapiaassignment.base.network.DataResult
import com.my.sapiaassignment.datalayer.apimodel.PetItem
import com.my.sapiaassignment.datalayer.repository.petslist.PetsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class PetsViewModel @Inject constructor(private val petsRepository: PetsRepository) :
    ViewModel() {
    private var _petsList = MutableLiveData<DataResult<List<PetItem>?>>()
    val petsList: LiveData<DataResult<List<PetItem>?>>
        get() = _petsList


    fun fetchPetsListData() {
        viewModelScope.launch {
            try {
                _petsList.value = DataResult.Loading
                val list = petsRepository.fetchPetsData()
                _petsList.value = list
            } catch (e: Exception) {
                _petsList.value = DataResult.Error(e)

            }
        }
    }

}