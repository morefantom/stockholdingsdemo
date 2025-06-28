package com.prathamesh.stockholdingsdemo.data.remote.source

import com.prathamesh.stockholdingsdemo.data.remote.model.PortfolioResponse
import retrofit2.http.GET

interface PortfolioService {
    @GET("/")
    suspend fun getPortfolio(): PortfolioResponse
}