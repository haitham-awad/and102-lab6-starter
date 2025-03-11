package com.codepath.articlesearch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "PopularArticleDetailActivity"

class MostPopularArticleDetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var abstractTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        abstractTextView = findViewById(R.id.mediaAbstract)

        val article = intent.getSerializableExtra(ARTICLE_EXTRA) as MostPopularArticle
        titleTextView.text = article.title
        abstractTextView.text = article.abstract

        Glide.with(this)
            .load(article.mediaImageUrl ?: "")
            .into(mediaImageView)
    }
}
