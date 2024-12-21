package com.astrick.sandbox.integrations.paging.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.astrick.sandbox.integrations.paging.data.GithubSearchRepoImpl
import com.astrick.sandbox.integrations.paging.data.local.RepoDatabase
import com.astrick.sandbox.integrations.paging.data.remote.GithubRemoteApi
import com.astrick.sandbox.integrations.paging.domain.GithubSearchRepo
import com.astrick.sandbox.integrations.paging.ui.ViewModelFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Provides dependencies for integrating paging with the application, including repositories and ViewModel factories.
 */
object PagingIntegrationInjection {

    /**
     * Creates and provides a [ViewModelProvider.Factory] configured with the required repository for paging.
     *
     * @param context The application context for initializing the repository and database.
     * @return A [ViewModelProvider.Factory] for creating ViewModels with paging integration.
     */
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }

    private fun provideGithubRepository(context: Context): GithubSearchRepo {
        val api = provideGithubRemoteApi()
        val db = RepoDatabase.getInstance(context)
        return GithubSearchRepoImpl(api, db)
    }

    private fun provideGithubRemoteApi(): GithubRemoteApi {
        val retrofit = provideRetrofit()
        return retrofit.create(GithubRemoteApi::class.java)
    }

    private fun provideRetrofit(): Retrofit {
        val client = provideOkHttpClient()
        val moshiConverterFactory = provideMoshiConverterFactory()

        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(moshiConverterFactory)
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = Level.BASIC
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }

    private fun provideMoshiConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        return MoshiConverterFactory
            .create(moshi)
    }

}
