//package com.fmq.mysamplekotlingraphql.viewmodel
//
//import FindCountriesOfAContinentQuery
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.apollographql.apollo3.api.ApolloResponse
//import com.fmq.mysamplekotlingraphql.repository.MainRepository
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//
//class MainViewModel(private val repository: MainRepository) : ViewModel() {
//
//    var data : MutableLiveData<GetContinentsQuery.Data?> = MutableLiveData()
//    var countryData : MutableLiveData<FindCountriesOfAContinentQuery.Data?> = MutableLiveData()
//
//    fun getContinentList() {
//        GlobalScope.launch(Dispatchers.IO) {
//            val result = getContinents()
//            if(result!=null) {
//                data.postValue(result.data)
//            }
//        }
//    }
//
//    private suspend fun getContinents() : ApolloResponse<GetContinentsQuery.Data> {
//        return repository.getContinents()
//    }
//
//    fun getCountriesList(code : String) {
//        GlobalScope.launch(Dispatchers.IO) {
//            val result = getContinent(code)
//            if(result!=null) {
//                countryData.postValue(result.data)
//            }
//        }
//    }
//
//    private suspend fun getContinent(code : String) : ApolloResponse<FindCountriesOfAContinentQuery.Data> {
//        return repository.getCountriesOfSelectedContinent(code)
//    }
//
//}