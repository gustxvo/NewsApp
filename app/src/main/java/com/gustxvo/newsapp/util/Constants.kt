package com.gustxvo.newsapp.util

import io.github.cdimascio.dotenv.dotenv

private val dotenv = dotenv {
    directory = "/assets"
    filename = "env"
}

val API_KEY: String = dotenv["API_KEY"]
const val BASE_URL = "https://newsapi.org"