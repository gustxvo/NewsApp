package com.gustxvo.newsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.gustxvo.newsapp.R
import com.gustxvo.newsapp.db.ArticleDatabase
import com.gustxvo.newsapp.repository.NewsRepository
import com.gustxvo.newsapp.ui.viewmodel.NewsViewModel
import com.gustxvo.newsapp.ui.viewmodel.NewsViewModelFactory

class SearchNewsFragment : Fragment() {

    private val viewModel: NewsViewModel by activityViewModels {
        NewsViewModelFactory(
            NewsRepository(ArticleDatabase(requireContext()))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_news, container, false)
    }
}