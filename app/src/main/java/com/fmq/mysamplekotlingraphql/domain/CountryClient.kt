package com.fmq.mysamplekotlingraphql.domain

import rx.Observable

interface CountryClient {
  //  suspend fun getCountries(code : String): List<SimpleCountry>
    suspend fun getContinent(): List<ContinentDetails>
}