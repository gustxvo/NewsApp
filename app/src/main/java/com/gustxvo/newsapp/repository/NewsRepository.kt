package com.gustxvo.newsapp.repository

import androidx.room.Query
import com.gustxvo.newsapp.api.RetrofitInstance
import com.gustxvo.newsapp.db.ArticleDatabase

class NewsRepository(val database: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.newsApi.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.newsApi.searchForNews(searchQuery, pageNumber)
}