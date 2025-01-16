package com.example.recipefinderapp

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val BASE_URL = "https://api.spoonacular.com/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY // Logs the body of the response
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor) // Add logging to OkHttp
        .connectTimeout(30, TimeUnit.SECONDS) // Optional: Set connection timeout
        .readTimeout(30, TimeUnit.SECONDS)   // Optional: Set read timeout
        .build()

    val instance: RecipeApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApi::class.java)
    }
}
