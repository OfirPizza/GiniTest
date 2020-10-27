package com.test.ginitest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://pastebin.com/"

val retrofitModule = module {
    factory<Converter.Factory> { GsonConverterFactory.create() }
    factory<CallAdapter.Factory> { RxJava2CallAdapterFactory.create() }
    factory { getRetrofit() }
    single<NetworkApi> { get<Retrofit>().create(NetworkApi::class.java) }
}

fun getOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
}

fun Scope.getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(getOkHttpClient())
        .addCallAdapterFactory(get<CallAdapter.Factory>())
        .addConverterFactory(get<Converter.Factory>())
        .build()


}


