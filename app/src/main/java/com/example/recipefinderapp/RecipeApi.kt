package com.example.recipefinderapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeApi {
    @GET("recipes/findByIngredients")
    fun getRecipes(
        @Query("ingredients") ingredients: String,
        @Query("apiKey") apiKey: String
    ): Call<List<Recipe>>

    @GET("recipes/{id}/information")
    fun getRecipeDetails(
        @Path("id") recipeId: Int,
        @Query("apiKey") apiKey: String
    ): Call<RecipeDetails>
}
