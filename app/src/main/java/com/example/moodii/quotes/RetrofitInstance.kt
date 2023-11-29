package com.example.moodii.quotes

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Define a singleton object named RetrofitInstance to set up and provide a Retrofit client.
object RetrofitInstance {
    // The base URL for the API.
    private const val BASE_URL = "https://api.api-ninjas.com"

    // Function to create and configure a Retrofit instance.
    private fun getRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            // Add an interceptor that will add headers to every HTTP request made by Retrofit.
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    // Add API key header for authentication
                    .addHeader("X-Api-Key", "wI+itsJhULsHnoh3x5ov3Q==zzY9UsNY3QDmY1ge")
                    .build()
                chain.proceed(newRequest)
            }
            .build()

        // Build and return a Retrofit instance.
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Lazy-initialized property to create the API interface implementation.
    val api: QuoteApi by lazy {
        getRetrofit().create(QuoteApi::class.java)
    }
}