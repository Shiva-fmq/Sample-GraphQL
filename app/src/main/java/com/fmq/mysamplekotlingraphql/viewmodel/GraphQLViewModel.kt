package com.fmq.mysamplekotlingraphql.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmq.mysamplekotlingraphql.domain.ContinentDetails
import com.fmq.mysamplekotlingraphql.domain.GetContinentUseCase
import com.fmq.mysamplekotlingraphql.domain.GetCountriesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class GraphQLViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getContinentUseCase: GetContinentUseCase
) : ViewModel() {

    var data : MutableLiveData<List<ContinentDetails>> = MutableLiveData()

    fun getContinentsList() {
        viewModelScope.launch {
            data.postValue(getContinentUseCase.execute())
        }
    }
}