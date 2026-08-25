package com.example.limitlesstech.limitlessnews.di
//Room
import androidx.room.Room
import com.example.limitlesstech.limitlessnews.data.local.room.bookmark.BookmarkDao
import com.example.limitlesstech.limitlessnews.data.repositoryImpl.BookmarkRepositoryImpl
import com.example.limitlesstech.limitlessnews.domain.repository.BookmarkRepository
// Hilt
import android.content.Context
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
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json

// Kotlinx Serialization
import kotlinx.serialization.json.Json

// Project imports
import com.example.limitlesstech.limitlessnews.core.network.NewsApi
import com.example.limitlesstech.limitlessnews.data.local.datastore.DataStoreManager
import com.example.limitlesstech.limitlessnews.data.repositoryImpl.FirebaseAuthRepository
import com.example.limitlesstech.limitlessnews.data.repositoryImpl.FirebaseProfileRepository
import com.example.limitlesstech.limitlessnews.data.repositoryImpl.NewsRepositoryImpl
import com.example.limitlesstech.limitlessnews.domain.repository.AuthRepository
import com.example.limitlesstech.limitlessnews.domain.repository.NewsRepository
import com.example.limitlesstech.limitlessnews.domain.usecase.news.GetTrendingNewsUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.http.encodedPath

// Javax
import javax.inject.Singleton
import com.example.limitlesstech.limitlessnews.domain.usecase.news.GetPagedNewsUseCase


import com.example.limitlesstech.limitlessnews.data.repositoryImpl.NetworkRepositoryImpl
import com.example.limitlesstech.limitlessnews.domain.repository.NetworkRepository
import com.example.limitlesstech.limitlessnews.domain.repository.ProfileRepository
import com.example.limitlesstech.limitlessnews.domain.usecase.profile.SaveProfileUseCase
import com.google.firebase.firestore.FirebaseFirestore
import com.example.limitlesstech.limitlessnews.core.network.CloudinaryUploader
import com.example.limitlesstech.limitlessnews.data.local.room.NewsDatabase

import com.example.limitlesstech.limitlessnews.domain.usecase.profile.GetProfileUseCase
import com.example.limitlesstech.limitlessnews.domain.usecase.profile.UpdateProfileUseCase



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
    fun provideGetTrendingNewsUseCase(repository: NewsRepository): GetTrendingNewsUseCase{
        return GetTrendingNewsUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetPagedNewsUseCase(
        repository: NewsRepository
    ): GetPagedNewsUseCase {

        return GetPagedNewsUseCase(repository)

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

    // 🔥 DataStore DI
    // 🔥 DataStoreManager
    @Provides
    @Singleton
    fun provideDataStoreManager(
        @ApplicationContext context: Context,
        firebaseAuth: FirebaseAuth
    ): DataStoreManager {

        return DataStoreManager(
            context = context,
            firebaseAuth = firebaseAuth
        )
    }
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): NewsDatabase {

        return Room.databaseBuilder(//db banao
            context,
            NewsDatabase::class.java,//kon si database class user karni h
            "news_db" //database ka naam
        )
            .fallbackToDestructiveMigration()//agar schema change hota h toh purana data delete kardo
            .build()
    }

    @Provides
    @Singleton
    fun provideBookmarkDao(
        db: NewsDatabase
    ): BookmarkDao {

        return db.bookmarkDao()
    }
    @Provides
    @Singleton
    fun provideBookmarkRepository(
        dao: BookmarkDao,
        firebaseAuth: FirebaseAuth
    ): BookmarkRepository {

        return BookmarkRepositoryImpl(
            dao = dao,
            firebaseAuth = firebaseAuth
        )
    }
    @Provides
    @Singleton
    fun provideNetworkRepository(
        @ApplicationContext context: Context
    ): NetworkRepository {
        return NetworkRepositoryImpl(context)
    }
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
    @Provides
    @Singleton
    fun provideCloudinaryUploader(
        @ApplicationContext context: Context
    ): CloudinaryUploader {
        return CloudinaryUploader(context)
    }
    @Provides
    @Singleton
    fun provideProfileRepository(
        firebaseAuth: FirebaseAuth,
        firestore: FirebaseFirestore,
        cloudinaryUploader: CloudinaryUploader
    ): ProfileRepository {

        return FirebaseProfileRepository(
            firebaseAuth = firebaseAuth,
            firestore = firestore,
            cloudinaryUploader = cloudinaryUploader
        )
    }
    @Provides
    @Singleton
    fun provideSaveProfileUseCase(
        repository: ProfileRepository
    ): SaveProfileUseCase {

        return SaveProfileUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideGetProfileUseCase(
        repository: ProfileRepository
    ): GetProfileUseCase {
        return GetProfileUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateProfileUseCase(
        repository: ProfileRepository
    ): UpdateProfileUseCase {
        return UpdateProfileUseCase(repository)
    }
}