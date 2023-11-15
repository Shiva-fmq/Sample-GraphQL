package com.fmq.mysamplekotlingraphql.domain

class GetContinentUseCase(private val countryClient: CountryClient) {
    suspend fun execute(): List<ContinentDetails> {
        return countryClient.getContinent()
    }
}