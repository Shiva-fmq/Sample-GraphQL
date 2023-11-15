package com.fmq.mysamplekotlingraphql.domain

class GetCountriesUseCase(private val countryClient: CountryClient) {
    suspend fun execute(code : String): List<SimpleCountry> {
        return countryClient
            .getCountries(code)
            .sortedBy { it.name }
    }
}