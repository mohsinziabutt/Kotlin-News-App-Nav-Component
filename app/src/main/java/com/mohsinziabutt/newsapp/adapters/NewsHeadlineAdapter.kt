package com.mohsinziabutt.newsapp.adapters

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mohsinziabutt.newsapp.R
import com.mohsinziabutt.newsapp.fragments.newsActivityMainFragments.NewsDetailsFragment
import com.mohsinziabutt.newsapp.responseModels.NewsArrayList


// if we use val or var in constructor variables act as properties
// and without var or val variables act as parameters
class NewsHeadlineAdapter(val context: Context, val news: List<NewsArrayList>) :
    RecyclerView.Adapter<NewsHeadlineAdapter.NewsViewHolder>() {

    var navController: NavController? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.news_headline_item,
            parent,
            false
        )
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val _newsHeadline = news[position]
        Glide.with(holder.itemView.getContext()).load(_newsHeadline.news_urlToImage)
            .placeholder(R.drawable.img_loading_anim).error(R.drawable.no_img).fitCenter()
            .into(holder.headlineImage);
        holder.headlineDescription.text = _newsHeadline.news_description
        holder.newsSource.text = _newsHeadline.news_source.name

        holder.headlineDetailButton.setOnClickListener{ view ->
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

    override fun getItemCount(): Int {
        return news.size
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var headlineImage = itemView.findViewById<ImageView>(R.id.headlineImage)
        var newsSource = itemView.findViewById<TextView>(R.id.newsSource)
        var headlineDescription = itemView.findViewById<TextView>(R.id.headlineDescription)
        var headlineDetailButton = itemView.findViewById<Button>(R.id.headlineDetailButton)
    }
}