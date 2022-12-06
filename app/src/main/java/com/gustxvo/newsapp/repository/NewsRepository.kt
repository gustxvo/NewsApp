package com.gustxvo.newsapp.repository

import com.gustxvo.newsapp.api.RetrofitInstance
import com.gustxvo.newsapp.db.ArticleDatabase
import com.gustxvo.newsapp.model.Article

class NewsRepository(val database: ArticleDatabase) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.newsApi.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.newsApi.searchForNews(searchQuery, pageNumber)

    fun getSavedNews() = database.articleDao().getAllArticles()

    suspend fun upsert(article: Article) =
        database.articleDao().upsert(article)

    suspend fun deleteArticle(article: Article) =
        database.articleDao().deleteArticle(article)
}