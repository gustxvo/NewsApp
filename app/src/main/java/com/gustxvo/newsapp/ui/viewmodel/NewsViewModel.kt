package com.gustxvo.newsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gustxvo.newsapp.repository.NewsRepository

class NewsViewModel(val newsRepository: NewsRepository) : ViewModel() {

}

class NewsViewModelFactory(private val newsRepository: NewsRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}