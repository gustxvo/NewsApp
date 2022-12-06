package com.gustxvo.newsapp.util

import io.github.cdimascio.dotenv.dotenv

private val dotenv = dotenv {
    directory = "/assets"
    filename = "env"
}

val API_KEY: String = dotenv["API_KEY"]
const val BASE_URL = "https://newsapi.org"
const val SEARCH_NEWS_TIME_DELAY = 500L
const val QUERY_PAGE_SIZE = 20