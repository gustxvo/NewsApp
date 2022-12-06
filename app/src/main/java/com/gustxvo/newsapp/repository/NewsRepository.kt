package com.gustxvo.newsapp.repository

import com.gustxvo.newsapp.api.RetrofitInstance
import com.gustxvo.newsapp.db.ArticleDatabase

class NewsRepository(val database: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.newsApi.getBreakingNews(countryCode, pageNumber)
}