//package com.fmq.mysamplekotlingraphql.repository
//
//import FindCountriesOfAContinentQuery
//import GetContinentsQuery
//import com.apollographql.apollo3.ApolloClient
//import com.apollographql.apollo3.api.ApolloResponse
//
//
//class MainRepository(private val apolloClient: ApolloClient){
//
//
//  //  suspend fun getContinents(apolloClient : ApolloClient) = apolloClient.query(GetContinentsQuery()).execute()
//
//
//
//    /*suspend fun getContinents(apolloClient : ApolloClient) : ApolloResponse<GetContinentsQuery.Data> {
//        return apolloClient.query(GetContinentsQuery()).execute()
//    }
//
//    suspend fun getCountriesOfSelectedContinent(continentCode: String, apolloClient : ApolloClient) : ApolloResponse<FindCountriesOfAContinentQuery.Data> {
//        return apolloClient.query(
//            FindCountriesOfAContinentQuery(continentCode)
//        ).execute()
//    }*/
//
//    suspend fun getContinents() : ApolloResponse<GetContinentsQuery.Data> {
//        return apolloClient.query(GetContinentsQuery()).execute()
//    }
//
//    suspend fun getCountriesOfSelectedContinent(continentCode: String) : ApolloResponse<FindCountriesOfAContinentQuery.Data> {
//        return apolloClient.query(
//            FindCountriesOfAContinentQuery(continentCode)
//        ).execute()
//    }
//}