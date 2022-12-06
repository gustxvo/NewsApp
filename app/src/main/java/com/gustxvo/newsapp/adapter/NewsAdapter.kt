package com.gustxvo.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gustxvo.newsapp.databinding.ArticlePreviewItemBinding
import com.gustxvo.newsapp.model.Article
import okhttp3.internal.notifyAll

class NewsAdapter(private val onItemClicked: (Article) -> Unit) : ListAdapter<Article, NewsAdapter.ArticleViewHolder>(DiffCallback) {

    inner class ArticleViewHolder(private val binding: ArticlePreviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.apply {
                tvTitle.text = article.title
                tvDescription.text = article.description
                tvPublishedAt.text = article.publishedAt
                tvSource.text = article.source.name
            }
            Glide.with(itemView).load(article.urlToImage) to binding.ivArticleImage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ArticlePreviewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)
        holder.bind(article)
        holder.itemView.setOnClickListener {
            onItemClicked(article)
        }
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Article>() {

            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }
}

