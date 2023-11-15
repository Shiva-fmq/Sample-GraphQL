package com.fmq.mysamplekotlingraphql.data

import GetContinentsQuery
import com.apollographql.apollo3.ApolloClient
import com.fmq.mysamplekotlingraphql.domain.ContinentDetails
import com.fmq.mysamplekotlingraphql.domain.CountryClient
import com.fmq.mysamplekotlingraphql.domain.DetailedCountry
import com.fmq.mysamplekotlingraphql.domain.SimpleCountry

class ApolloCountryClient(private val apolloClient: ApolloClient) : CountryClient {
   /* override suspend fun getCountries(code : String): List<SimpleCountry> {
        return apolloClient
            .query(FindCountriesOfAContinentQuery(code))
            .execute()
            .data
            ?.continent
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }*/

    override suspend fun getContinent(): List<ContinentDetails> {
        return apolloClient
            .query(GetContinentsQuery())
            .execute()
            .data
            ?.continents
            ?.map { it.toContinent() }
            ?: emptyList()
    }

}