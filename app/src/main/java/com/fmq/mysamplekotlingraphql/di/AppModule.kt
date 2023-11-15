package com.fmq.mysamplekotlingraphql.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.fmq.mysamplekotlingraphql.data.ApolloCountryClient
import com.fmq.mysamplekotlingraphql.domain.CountryClient
import com.fmq.mysamplekotlingraphql.domain.GetContinentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        val okHttpClient = OkHttpClient.Builder().build()
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .okHttpClient(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetContinentUseCase(countryClient: CountryClient): GetContinentUseCase {
        return GetContinentUseCase(countryClient)
    }

}