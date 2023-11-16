package com.fmq.mysamplekotlingraphql.domain

import rx.Observable

class GetContinentUseCase(private val countryClient: CountryClient) {
    suspend fun execute(): List<ContinentDetails> {
        return countryClient.getContinent()
    }
}