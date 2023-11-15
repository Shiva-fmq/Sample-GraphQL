package com.fmq.mysamplekotlingraphql.domain

interface CountryClient {
  //  suspend fun getCountries(code : String): List<SimpleCountry>
    suspend fun getContinent(): List<ContinentDetails>
}