package com.codepath.articlesearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val TAG = "MostPopularArticleAdapter"

class MostPopularArticleAdapter(private val context: Context, private val mostPopularArticles: List<MostPopularArticle>) :
    RecyclerView.Adapter<MostPopularArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = mostPopularArticles[position]
        holder.bind(article)
    }

    override fun getItemCount() = mostPopularArticles.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val mediaImageView = itemView.findViewById<ImageView>(R.id.mediaImage)
        private val titleTextView = itemView.findViewById<TextView>(R.id.mediaTitle)
        private val abstractTextView = itemView.findViewById<TextView>(R.id.mediaAbstract)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(article: MostPopularArticle) {
            titleTextView.text = article.title
            abstractTextView.text = article.abstract

            Glide.with(context)
                .load(article.mediaImageUrl)
                .into(mediaImageView)
        }

        override fun onClick(v: View?) {
            val article = mostPopularArticles[absoluteAdapterPosition]

            val intent = Intent(context, MostPopularArticleDetailActivity::class.java)
            intent.putExtra(ARTICLE_EXTRA, article)
            context.startActivity(intent)
        }
    }
}
