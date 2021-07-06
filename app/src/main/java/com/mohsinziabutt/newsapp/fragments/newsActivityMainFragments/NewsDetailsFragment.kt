package com.mohsinziabutt.newsapp.fragments.newsActivityMainFragments

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.mohsinziabutt.newsapp.R
import kotlinx.android.synthetic.main.fragment_news_details.*
import kotlinx.android.synthetic.main.fragment_news_details.view.*
import java.text.SimpleDateFormat
import java.util.*

class NewsDetailsFragment : Fragment() {

    lateinit var newsUrl: String
    lateinit var newsImgUrl: String
    lateinit var newsSource: String
    lateinit var newsTitle: String
    lateinit var newsDate: String
    lateinit var newsContent: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        newsUrl = arguments?.getString("url").toString()
        newsImgUrl = arguments?.getString("img_url").toString()
        newsSource = arguments?.getString("source").toString()
        newsTitle = arguments?.getString("title").toString()
        newsDate = arguments?.getString("date").toString()
        newsContent = arguments?.getString("content").toString()

        val list = newsContent.split('[')
        newsContent = list[0]

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val output = SimpleDateFormat("yyyy-MM-dd (hh:mm aa)")
        val d = sdf.parse(newsDate)
        val formattedTime = output.format(d)

        newsDate = formattedTime
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this).load(newsImgUrl)
            .placeholder(R.drawable.img_loading_anim).error(R.drawable.no_img).fitCenter()
            .into(view.newsImage)
        view.newsSourceText.text = newsSource
        view.newsDate.text = newsDate
        view.newsTitle.text = newsTitle
        view.newsContent.text = newsContent

        view.visitNewsSourceButton.setOnClickListener { view ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(newsUrl)
            startActivity(intent)
        }
    }
}