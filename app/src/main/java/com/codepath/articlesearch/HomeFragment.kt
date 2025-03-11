package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import kotlinx.serialization.json.Json
import okhttp3.Headers
import org.json.JSONException

private const val LOG_TAG = "HomeFragment"
private const val API_KEY = BuildConfig.API_KEY
private const val NYT_POPULAR_ARTICLES_URL =
    "https://api.nytimes.com/svc/mostpopular/v2/viewed/1.json?api-key=$API_KEY"

class HomeFragment : Fragment() {

    private val articleList = mutableListOf<MostPopularArticle>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: MostPopularArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = rootView.findViewById(R.id.article_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }

        articleAdapter = MostPopularArticleAdapter(rootView.context, articleList)
        recyclerView.adapter = articleAdapter

        loadArticles()

        return rootView
    }

    private fun loadArticles() {
        val httpClient = AsyncHttpClient()
        httpClient.get(NYT_POPULAR_ARTICLES_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(LOG_TAG, "Error fetching articles: $statusCode", throwable)
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d(LOG_TAG, "Received JSON Response: ${json.jsonObject.toString(4)}")
                try {
                    val parsedData = Json { ignoreUnknownKeys = true }
                        .decodeFromString(MostPopularNewsResponse.serializer(), json.jsonObject.toString())

                    parsedData.results?.let { articles ->
                        articleList.addAll(articles)
                        articleAdapter.notifyDataSetChanged()
                    }
                } catch (e: JSONException) {
                    Log.e(LOG_TAG, "JSON Parsing Error: $e")
                }
            }
        })
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}
