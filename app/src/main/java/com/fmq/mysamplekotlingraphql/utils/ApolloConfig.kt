package com.fmq.mysamplekotlingraphql.utils

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import com.apollographql.apollo3.network.okHttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import okhttp3.OkHttpClient

class ApolloConfig  {

    companion object {
        fun getApolloClient(): ApolloClient {
            val okHttpClient = OkHttpClient.Builder().build()
            return ApolloClient.Builder()
                .serverUrl(AppConstants.BASE_URL)
                .addInterceptor(LoggingApolloInterceptor())
                .okHttpClient(okHttpClient)
                .build()
        }
    }

}

class LoggingApolloInterceptor: ApolloInterceptor {
    override fun <D : Operation.Data> intercept(request: ApolloRequest<D>, chain: ApolloInterceptorChain): Flow<ApolloResponse<D>> {
        return chain.proceed(request).onEach { response ->
            println("Received response for Operation Name = ${request.operation.name()} \n" +
                    "Response Data =  ${response.data}")
        }
    }
}