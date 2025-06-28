package com.prathamesh.stockholdingsdemo.data.remote.di

import com.prathamesh.stockholdingsdemo.data.remote.source.PortfolioService
import com.prathamesh.stockholdingsdemo.data.remote.source.RemoteSource
import com.prathamesh.stockholdingsdemo.data.remote.source.RemoteSourceImpl
import com.prathamesh.stockholdingsdemo.data.remote.source.RetrofitInstanceProvider
import com.prathamesh.stockholdingsdemo.data.remote.source.RetrofitInstanceProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideRetrofitInstanceProvider(): RetrofitInstanceProvider {
        return RetrofitInstanceProviderImpl()
    }

    @Singleton
    @Provides
    fun provideRemoteSource(
        retrofitInstanceProvider: RetrofitInstanceProvider,
    ): RemoteSource {
        return RemoteSourceImpl(
            retrofitInstanceProvider = retrofitInstanceProvider,
        )
    }

    @Singleton
    @Provides
    fun providePortfolioService(
        remoteSource: RemoteSource,
    ): PortfolioService {
        return remoteSource.portfolioService()
    }
}