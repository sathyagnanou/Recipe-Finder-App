package com.example.recipefinderapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the correct layout resource ID
        setContentView(R.layout.activity_recipe_detail)

        val title = intent.getStringExtra("RECIPE_TITLE")
        val image = intent.getStringExtra("RECIPE_IMAGE")

        val titleView: TextView = findViewById(R.id.recipeDetailTitle)
        val imageView: ImageView = findViewById(R.id.recipeDetailImage)

        titleView.text = title
        Glide.with(this).load(image).into(imageView)
    }
}
