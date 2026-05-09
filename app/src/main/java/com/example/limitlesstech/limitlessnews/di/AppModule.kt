package com.example.limitlesstech.limitlessnews.di

// Hilt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

// Ktor
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.*
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json

// Kotlinx Serialization
import kotlinx.serialization.json.Json

// Project imports
import com.example.limitlesstech.limitlessnews.core.network.NewsApi
import com.example.limitlesstech.limitlessnews.data.repositoryImpl.FirebaseAuthRepository
import com.example.limitlesstech.limitlessnews.data.repositoryImpl.NewsRepositoryImpl
import com.example.limitlesstech.limitlessnews.domain.repository.AuthRepository
import com.example.limitlesstech.limitlessnews.domain.repository.NewsRepository
import com.example.limitlesstech.limitlessnews.domain.usecase.GetNewsUseCase
import com.google.firebase.auth.FirebaseAuth
import io.ktor.http.encodedPath

// Javax
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        coerceInputValues = true
                    }
                )
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 25_000
                connectTimeoutMillis = 25_000
                socketTimeoutMillis = 25_000
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "newsapi.org"
                    encodedPath = "/v2/"
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
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }


    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository {
        return FirebaseAuthRepository(firebaseAuth)
    }


}