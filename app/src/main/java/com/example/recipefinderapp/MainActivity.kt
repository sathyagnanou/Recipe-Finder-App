package com.example.recipefinderapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var etIngredients: EditText
    private lateinit var btnFetchRecipes: Button
    private lateinit var rvRecipes: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etIngredients = findViewById(R.id.etIngredients)
        btnFetchRecipes = findViewById(R.id.btnFetchRecipes)
        rvRecipes = findViewById(R.id.rvRecipes)

        rvRecipes.layoutManager = LinearLayoutManager(this)

        btnFetchRecipes.setOnClickListener {
            val ingredients = etIngredients.text.toString().trim()
            if (ingredients.isNotEmpty()) {
                fetchRecipes(ingredients)
            } else {
                Toast.makeText(this, "Please enter ingredients!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchRecipes(ingredients: String) {
        val apiKey = "695c1b234a1944fdbfbf8779cf52c04f"

        ApiClient.instance.getRecipes(ingredients, apiKey = apiKey)
            .enqueue(object : Callback<List<Recipe>> {
                override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                    if (response.isSuccessful) {
                        val recipes = response.body() ?: emptyList()
                        if (recipes.isNotEmpty()) {
                            rvRecipes.adapter = RecipeAdapter(recipes) { recipe ->
                                navigateToRecipeDetail(recipe)
                            }
                        } else {
                            Toast.makeText(this@MainActivity, "No recipes found!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Error fetching recipes!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failed to fetch data: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun navigateToRecipeDetail(recipe: Recipe) {
        val intent = Intent(this, RecipeDetailActivity::class.java).apply {
            putExtra("RECIPE_ID", recipe.id)
            putExtra("RECIPE_TITLE", recipe.title) // Key must match
            putExtra("RECIPE_IMAGE", recipe.image) // Key must match

        }
        startActivity(intent)
    }
}
