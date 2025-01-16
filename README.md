# Recipe Finder App

The **Recipe Finder App** is a user-friendly Android application that allows users to search for recipes based on ingredients they have at home. It provides detailed recipe information, including images, instructions, and the ability to capture and save photos for personalized recipe experiences.

---

## Features

- **Ingredient-Based Recipe Search**: Enter ingredients and get recipes tailored to what you have.
- **Detailed Recipe Information**: View recipe details, including title, instructions, and images.
- **Photo Capture**: Capture photos of your creations and save them under the recipe details.
- **Modern UI**: Clean and professional user interface with smooth navigation.
- **Offline Access**: View saved recipe images and photos even without an internet connection.

---

## Technologies Used

- **Languages**: Kotlin
- **UI Components**: XML Layouts
- **Networking**: Retrofit for API calls
- **Image Loading**: Glide for efficient image loading
- **RecyclerView**: For displaying recipe lists
- **API**: [Spoonacular API](https://spoonacular.com/food-api) for fetching recipe data
- **Permissions**: Camera and Internet access for photo capture and data retrieval

---

## Installation

1. Clone the repository:
   ```bash
   https://github.com/sathyagnanou/Recipe-Finder-App.git
   ```
2. Open the project in **Android Studio**.
3. Sync the Gradle files to install dependencies.
4. Replace the placeholder API key in `ApiClient.kt` with your Spoonacular API key:
   ```kotlin
   private const val API_KEY = "your_api_key_here"
   ```
5. Build and run the app on an emulator or physical device.

---

## How to Use

1. **Search for Recipes**:
   - Enter the ingredients in the search bar on the main screen.
   - Press the "Search" button to fetch a list of recipes.

2. **View Recipe Details**:
   - Tap on a recipe from the list to view its detailed information.
   - View the recipe image, title, and instructions.

3. **Capture a Photo**:
   - On the recipe detail page, tap "Take Photo" to capture and save an image.
   - The photo will appear under the recipe details.

---
