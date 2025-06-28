package com.prathamesh.stockholdingsdemo.data

import com.prathamesh.stockholdingsdemo.data.remote.source.PortfolioService
import com.prathamesh.stockholdingsdemo.data.remote.source.RemoteSourceImpl
import com.prathamesh.stockholdingsdemo.data.remote.source.RetrofitInstanceProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit

class RemoteSourceImplTest {

    private lateinit var retrofitInstanceProvider: RetrofitInstanceProvider
    private lateinit var retrofit: Retrofit
    private lateinit var portfolioService: PortfolioService

    private lateinit var remoteSource: RemoteSourceImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        retrofitInstanceProvider = mock(RetrofitInstanceProvider::class.java)
        retrofit = mock(Retrofit::class.java)
        portfolioService = mock(PortfolioService::class.java)

        `when`(retrofitInstanceProvider.provide()).thenReturn(retrofit)
        `when`(retrofit.create(PortfolioService::class.java)).thenReturn(portfolioService)

        remoteSource = RemoteSourceImpl(retrofitInstanceProvider)
    }

    @Test
    fun `portfolioService should return PortfolioService from retrofit`() {
        // Act
        val result = remoteSource.portfolioService()

        // Assert
        assert(result === portfolioService)

        // Verify interactions
        verify(retrofitInstanceProvider).provide()
        verify(retrofit).create(PortfolioService::class.java)
    }
}