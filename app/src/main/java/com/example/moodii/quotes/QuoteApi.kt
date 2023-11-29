package com.example.moodii.quotes

import retrofit2.http.GET
import retrofit2.http.Query

// Defines QuoteApi interface; fetches quotes from the API based on the category given as an argument
interface QuoteApi {
    @GET("/v1/quotes")
    suspend fun getRandomQuote(@Query("category") category: String?): List<Quote>
}