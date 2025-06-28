package com.prathamesh.stockholdingsdemo.data

import com.prathamesh.stockholdingsdemo.data.remote.RemoteConstants
import com.prathamesh.stockholdingsdemo.data.remote.source.RetrofitInstanceProviderImpl
import junit.framework.Assert.assertEquals
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import org.junit.Test
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstanceProviderImplTest {

    @Test
    fun `provide should return Retrofit instance with correct baseUrl`() {
        // Arrange
        val expectedBaseUrl: HttpUrl = RemoteConstants.BASE_URL.toHttpUrl()
        val provider = RetrofitInstanceProviderImpl()

        // Act
        val retrofit: Retrofit = provider.provide()

        // Assert
        assertEquals(expectedBaseUrl, retrofit.baseUrl())
        assert(retrofit.converterFactories().any { it is GsonConverterFactory })
    }

    @Test
    fun `provide should use passed converter factory`() {
        // Arrange
        val mockConverterFactory = DummyConverterFactory()
        val provider = RetrofitInstanceProviderImpl(converterFactory = mockConverterFactory)

        // Act
        val retrofit = provider.provide()

        // Assert
        assert(retrofit.converterFactories().contains(mockConverterFactory))
    }

    // Dummy converter to test custom factory injection
    class DummyConverterFactory : Converter.Factory()
}