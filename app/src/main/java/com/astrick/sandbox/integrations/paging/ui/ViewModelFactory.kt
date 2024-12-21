package com.astrick.sandbox.integrations.paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.astrick.sandbox.integrations.paging.domain.GithubSearchRepo

class ViewModelFactory(private val repository: GithubSearchRepo) : ViewModelProvider.Factory {
    
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GithubSearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GithubSearchViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
