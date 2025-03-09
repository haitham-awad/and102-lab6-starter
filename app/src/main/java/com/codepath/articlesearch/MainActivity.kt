package com.codepath.articlesearch

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.json.Json

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "MainActivity/"
private const val PREFS_NAME = "AppPrefs"
private const val KEY_BACKGROUND_COLOR = "background_color"
private const val DEFAULT_COLOR = Color.WHITE
private var TOGGLE_COLOR = Color.parseColor("#FFBB86FC") // Pick any color

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // Apply saved background color
        val savedColor = sharedPreferences.getInt(KEY_BACKGROUND_COLOR, DEFAULT_COLOR)
        binding.root.setBackgroundColor(savedColor)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_books -> BestSellerBooksFragment()
                R.id.nav_articles -> ArticleListFragment()
                R.id.nav_settings -> SettingsFragment()
                R.id.nav_home -> HomeFragment()
                else -> return@setOnItemSelectedListener false
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_books
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.article_frame_layout, fragment)
            .commit()
    }

    fun toggleBackgroundColor() {
        val currentColor = sharedPreferences.getInt(KEY_BACKGROUND_COLOR, DEFAULT_COLOR)
        val newColor = if (currentColor == DEFAULT_COLOR) TOGGLE_COLOR else DEFAULT_COLOR

        binding.root.setBackgroundColor(newColor)
        sharedPreferences.edit().putInt(KEY_BACKGROUND_COLOR, newColor).apply()
    }
}
