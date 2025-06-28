package com.prathamesh.stockholdingsdemo.data.remote.source

import com.prathamesh.stockholdingsdemo.data.remote.RemoteConstants
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitInstanceProvider {
    fun provide(): Retrofit
}

class RetrofitInstanceProviderImpl(
    private val converterFactory: Converter.Factory = GsonConverterFactory.create()
) : RetrofitInstanceProvider {

    override fun provide(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RemoteConstants.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
    }
}