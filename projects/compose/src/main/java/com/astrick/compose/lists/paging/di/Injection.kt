package com.astrick.compose.lists.paging.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.astrick.compose.lists.paging.data.remote.GithubSearchRemoteDataSource
import com.astrick.compose.lists.paging.data.GithubSearchRepository
import com.astrick.compose.lists.paging.data.local.RepoDatabase
import com.astrick.compose.lists.paging.ui.ViewModelFactory

object Injection {
    
    fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
        return ViewModelFactory(provideGithubRepository(context))
    }
    
    private fun provideGithubRepository(context: Context): GithubSearchRepository {
        return GithubSearchRepository(GithubSearchRemoteDataSource.create(), RepoDatabase.getInstance(context))
    }
    
}
