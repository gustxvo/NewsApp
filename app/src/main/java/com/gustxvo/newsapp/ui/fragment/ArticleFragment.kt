package com.gustxvo.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.gustxvo.newsapp.databinding.FragmentArticleBinding
import com.gustxvo.newsapp.db.ArticleDatabase
import com.gustxvo.newsapp.repository.NewsRepository
import com.gustxvo.newsapp.ui.viewmodel.NewsViewModel
import com.gustxvo.newsapp.ui.viewmodel.NewsViewModelFactory

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null

    private val binding get() = _binding!!

    private val args: ArticleFragmentArgs by navArgs()

    private val viewModel: NewsViewModel by activityViewModels {
        NewsViewModelFactory(
            NewsRepository(ArticleDatabase(requireContext()))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(article.url)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}