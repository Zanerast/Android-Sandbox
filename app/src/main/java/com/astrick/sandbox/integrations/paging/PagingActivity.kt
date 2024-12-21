package com.astrick.sandbox.integrations.paging

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.astrick.sandbox.integrations.paging.di.PagingIntegrationInjection
import com.astrick.sandbox.integrations.paging.ui.GithubSearchViewModel

class SearchRepositoriesActivity : AppCompatActivity() {

    private lateinit var viewModel: GithubSearchViewModel

    private var query = DEFAULT_QUERY
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        viewModel = ViewModelProvider(
            this, PagingIntegrationInjection.provideViewModelFactory(this)
        )[GithubSearchViewModel::class.java]
        
        query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        initSearch(query)
        
        setContent {
            PagingScreen(viewModel = viewModel, initialQuery = query)
        }
    }
    
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LAST_SEARCH_QUERY, query)
    }
    
    private fun initSearch(query: String) {
        this.query = query
        viewModel.searchForQuery(query)
    }
    
    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "Android"
    }
    
}
