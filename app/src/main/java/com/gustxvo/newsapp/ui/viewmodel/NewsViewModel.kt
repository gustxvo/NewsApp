package com.gustxvo.newsapp.ui.viewmodel

import androidx.lifecycle.*
import com.gustxvo.newsapp.model.NewsResponse
import com.gustxvo.newsapp.repository.NewsRepository
import com.gustxvo.newsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    private val _breakingNews = MutableLiveData<Resource<NewsResponse>>()

    val breakingNews: LiveData<Resource<NewsResponse>> = _breakingNews

    private var breakingNewsPage = MutableLiveData<Int>()

    fun getBreakingNews(countryCode: String) {
        viewModelScope.launch {
            _breakingNews.postValue(Resource.Loading())

            val response = newsRepository.getBreakingNews(
                countryCode, breakingNewsPage.value ?: 1
            )
            _breakingNews.postValue(handleBreakingNewsResponse(response))
        }
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}

class NewsViewModelFactory(private val newsRepository: NewsRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsRepository) as T
    }
}