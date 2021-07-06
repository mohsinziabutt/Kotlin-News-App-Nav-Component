package com.mohsinziabutt.newsapp.fragments.newsActivityMainFragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mohsinziabutt.firstkotlinproject.api.GetNewsClient
import com.mohsinziabutt.newsapp.R
import com.mohsinziabutt.newsapp.adapters.NewsCommonAdapter
import com.mohsinziabutt.newsapp.adapters.NewsHeadlineAdapter
import com.mohsinziabutt.newsapp.adapters.NewsNavBarAdapter
import com.mohsinziabutt.newsapp.responseModels.NewsArrayList
import com.mohsinziabutt.newsapp.responses.NewsResponse
import kotlinx.android.synthetic.main.fragment_news_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsMainFragment : Fragment() {

    lateinit var newsHeadlineAdapter: NewsHeadlineAdapter
    lateinit var newsCommonAdapter: NewsCommonAdapter
    lateinit var newsNavBarAdapter: NewsNavBarAdapter

    var newsHeadlineArrayList: ArrayList<NewsArrayList> = ArrayList()
    var newsCommonArrayList: ArrayList<NewsArrayList> = ArrayList()
    var newsNavBarArrayList: ArrayList<NewsArrayList> = ArrayList()

    var newsArticleLength: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getHeadlineNews()
        getCommonNews()
        getNavBarNews()
    }

    fun getHeadlineNews()
    {
        GetNewsClient.instance.getAllHeadlineNews().enqueue(object: Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>, response: Response<NewsResponse>
            ) {
                val allNews = response.body()

                if(allNews != null)
                {
                    newsArticleLength = allNews.articles.count()
//                    Log.d("NEWS_RESPONSE", "" + allNews.articles)

                    for (i in 0 until newsArticleLength)
                    {
                        val source = allNews.articles[i].source
                        val author = allNews.articles[i].author
                        val title = allNews.articles[i].title
                        val description = allNews.articles[i].description
                        val url = allNews.articles[i].url
                        val urlToImage = allNews.articles[i].urlToImage
                        val publishedAt = allNews.articles[i].publishedAt
                        val content = allNews.articles[i].content

                        val singleNewsHeadlineModel = NewsArrayList(source, author, title, description, url, urlToImage, publishedAt, content)

                        newsHeadlineArrayList.add(singleNewsHeadlineModel)
                        newsHeadlineAdapter = NewsHeadlineAdapter(context!!, newsHeadlineArrayList)
                        newsHeadlineAdapter.notifyDataSetChanged()

                    }
                    headlineRecyclerView.adapter = newsHeadlineAdapter
                    headlineRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
//                    scrollRecyclerView()
                }
                else
                {
                    Toast.makeText(context, "no data", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("NEWS_RESPONSE", "" + t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getCommonNews()
    {
        GetNewsClient.instance.getCommonNews().enqueue(object: Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>, response: Response<NewsResponse>
            ) {
                val allNews = response.body()

                if(allNews != null)
                {
                    newsArticleLength = allNews.articles.count()
//                    Log.d("NEWS_RESPONSE", "" + newsArticleLength)

                    for (i in 0 until newsArticleLength)
                    {
                        val source = allNews.articles[i].source
                        val author = allNews.articles[i].author
                        val title = allNews.articles[i].title
                        val description = allNews.articles[i].description
                        val url = allNews.articles[i].url
                        val urlToImage = allNews.articles[i].urlToImage
                        val publishedAt = allNews.articles[i].publishedAt
                        val content = allNews.articles[i].content

                        val singleNewsHeadlineModel = NewsArrayList(source, author, title, description, url, urlToImage, publishedAt, content)

                        newsCommonArrayList.add(singleNewsHeadlineModel)
                        newsCommonAdapter = NewsCommonAdapter(context!!, newsCommonArrayList)
                        newsCommonAdapter.notifyDataSetChanged()

                    }
                    newsCommonRecyclerView.adapter = newsCommonAdapter
                    newsCommonRecyclerView.layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL ,false)
//                    scrollRecyclerView()
                }
                else
                {
                    Toast.makeText(context, "no data", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("NEWS_RESPONSE", "" + t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getNavBarNews()
    {
        GetNewsClient.instance.getNavBarNews().enqueue(object: Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>, response: Response<NewsResponse>
            ) {
                val allNews = response.body()

                if(allNews != null)
                {
                    newsArticleLength = allNews.articles.count()
//                    Log.d("NEWS_RESPONSE", "" + allNews.articles)

                    for (i in 0 until newsArticleLength)
                    {
                        val source = allNews.articles[i].source
                        val author = allNews.articles[i].author
                        val title = allNews.articles[i].title
                        val description = allNews.articles[i].description
                        val url = allNews.articles[i].url
                        val urlToImage = allNews.articles[i].urlToImage
                        val publishedAt = allNews.articles[i].publishedAt
                        val content = allNews.articles[i].content

                        val singleNewsHeadlineModel = NewsArrayList(source, author, title, description, url, urlToImage, publishedAt, content)

                        newsNavBarArrayList.add(singleNewsHeadlineModel)
                        newsNavBarAdapter = NewsNavBarAdapter(context!!, newsNavBarArrayList)
                        newsNavBarAdapter.notifyDataSetChanged()

                    }
                    newsNavBarRecyclerView.adapter = newsNavBarAdapter
                    newsNavBarRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
//                    scrollRecyclerView()
                }
                else
                {
                    Toast.makeText(context, "no data", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.d("NEWS_RESPONSE", "" + t.message)
                Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}