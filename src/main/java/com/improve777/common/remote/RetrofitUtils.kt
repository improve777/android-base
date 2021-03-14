package com.improve777.common.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

fun createRetrofit(
    url: String,
    okHttpClient: OkHttpClient,
    converterFactory: Converter.Factory,
    callAdapterFactory: CallAdapter.Factory? = null,
) = Retrofit.Builder()
    .baseUrl(url)
    .client(okHttpClient)
    .addConverterFactory(converterFactory)
    .apply {
        if (callAdapterFactory != null) {
            addCallAdapterFactory(callAdapterFactory)
        }
    }
    .build()

fun createLoggingInterceptor(isDebug: Boolean) = HttpLoggingInterceptor().apply {
    level = if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
}