
package com.example.limitlesstech.limitlessnews.di
import com.example.limitlesstech.limitlessnews.core.network.NewsApi
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json

import kotlinx.serialization.json.Json

import com.example.limitlesstech.limitlessnews.data.repositoryImpl.NewsRepositoryImpl
import com.example.limitlesstech.limitlessnews.domain.repository.NewsRepository
import com.example.limitlesstech.limitlessnews.domain.usecase.GetNewsUseCase
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    coerceInputValues = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 25000 // 25 seconds
                connectTimeoutMillis = 25000 //
                socketTimeoutMillis = 25000
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "newsapi.org"//only domain name
                    encodedPath = "/v2/"//base path


                }
            }
        }
    }

    @Provides
    @Singleton
    fun provideNewsApi(client: HttpClient): NewsApi {
        return NewsApi(client)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(api: NewsApi): NewsRepository {
        return NewsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetNewsUseCase(repository: NewsRepository): GetNewsUseCase {
        return GetNewsUseCase(repository)

    }
}