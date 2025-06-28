package com.prathamesh.stockholdingsdemo.data.remote.source

interface RemoteSource {
    fun portfolioService(): PortfolioService
}

class RemoteSourceImpl(
    private val retrofitInstanceProvider: RetrofitInstanceProvider,
): RemoteSource {
    override fun portfolioService(): PortfolioService {
        return retrofitInstanceProvider
            .provide()
            .create(PortfolioService::class.java)
    }
}