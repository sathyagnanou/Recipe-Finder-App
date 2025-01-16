package com.example.recipefinderapp

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var photoView: ImageView
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val recipeId = intent.getIntExtra("RECIPE_ID", -1)

        if (recipeId != -1) {
            fetchRecipeDetails(recipeId)
        } else {
            Toast.makeText(this, "Recipe details not available!", Toast.LENGTH_SHORT).show()
        }

        // Set up the photo functionality
        val btnTakePhoto: Button = findViewById(R.id.btnTakePhoto)
        photoView = findViewById(R.id.photoView)

        btnTakePhoto.setOnClickListener {
            openCamera()
        }
    }

    private fun fetchRecipeDetails(recipeId: Int) {
        val apiKey = "695c1b234a1944fdbfbf8779cf52c04f"

        ApiClient.instance.getRecipeDetails(recipeId, apiKey).enqueue(object : Callback<RecipeDetails> {
            override fun onResponse(call: Call<RecipeDetails>, response: Response<RecipeDetails>) {
                if (response.isSuccessful) {
                    val recipe = response.body()
                    if (recipe != null) {
                        displayRecipeDetails(recipe)
                    }
                } else {
                    Toast.makeText(this@RecipeDetailActivity, "Error loading details!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RecipeDetails>, t: Throwable) {
                Toast.makeText(this@RecipeDetailActivity, "Failed to load data: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun displayRecipeDetails(recipe: RecipeDetails) {
        val titleView: TextView = findViewById(R.id.recipeDetailTitle)
        val imageView: ImageView = findViewById(R.id.recipeDetailImage)
        val instructionsView: TextView = findViewById(R.id.recipeInstructions)

        titleView.text = recipe.title
        instructionsView.text = recipe.instructions
        Glide.with(this).load(recipe.image).into(imageView)
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } else {
            Toast.makeText(this, "Camera not available!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            photoView.setImageBitmap(imageBitmap)
            photoView.visibility = ImageView.VISIBLE
        }
    }
}
