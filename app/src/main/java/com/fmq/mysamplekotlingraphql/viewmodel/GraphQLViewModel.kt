package com.fmq.mysamplekotlingraphql.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fmq.mysamplekotlingraphql.domain.ContinentDetails
import com.fmq.mysamplekotlingraphql.domain.GetContinentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import rx.Observable
import javax.inject.Inject

@HiltViewModel
class GraphQLViewModel @Inject constructor(
    private val getContinentUseCase: GetContinentUseCase
) : ViewModel() {

    private var _data : MutableLiveData<List<ContinentDetails>?> = MutableLiveData()
    var data : MutableLiveData<List<ContinentDetails>?> = _data



    fun getContinentsList() {
        viewModelScope.launch {
            _data.postValue(getContinentUseCase.execute())
        }
    }

}