package com.mohsinziabutt.newsapp.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mikhaellopez.circularimageview.CircularImageView
import com.mohsinziabutt.newsapp.R
import com.mohsinziabutt.newsapp.responseModels.NewsArrayList

// if we use val or var in constructor variables act as properties
// and without var or val variables act as parameters
class NewsNavBarAdapter(val context: Context, val news: List<NewsArrayList>) :
    RecyclerView.Adapter<NewsNavBarAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_nav_bar_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val circularProgressDrawable = CircularProgressDrawable(context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        val _newsHeadline = news[position]
        for (i in 0 until news.count())
        {
            Glide.with(holder.itemView.getContext()).load(_newsHeadline.news_urlToImage)
                .placeholder(R.drawable.img_loading_anim).error(R.drawable.no_img)
                .into(holder.newsNavBarImage);
            holder.newsNavBarAuthorName.text  = _newsHeadline.news_source.name

            holder.newsNavBarImage.setOnClickListener{ view ->
                val bundle = Bundle()
                bundle.putString("url", _newsHeadline.news_url)
                bundle.putString("img_url", _newsHeadline.news_urlToImage)
                bundle.putString("source", _newsHeadline.news_source.name)
                bundle.putString("title", _newsHeadline.news_title)
                bundle.putString("date", _newsHeadline.news_publishedAt)
                bundle.putString("content", _newsHeadline.news_content)

                view.findNavController().navigate(R.id.action_newsMainFragment_to_newsDetailsFragment2, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return news.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var newsNavBarImage = itemView.findViewById<CircularImageView>(R.id.newsNavBarImage)
        var newsNavBarAuthorName = itemView.findViewById<TextView>(R.id.newsNavBarAuthorName)
    }
}