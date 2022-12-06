package com.gustxvo.newsapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gustxvo.newsapp.R
import com.gustxvo.newsapp.adapter.NewsAdapter
import com.gustxvo.newsapp.databinding.FragmentSearchNewsBinding
import com.gustxvo.newsapp.db.ArticleDatabase
import com.gustxvo.newsapp.repository.NewsRepository
import com.gustxvo.newsapp.ui.viewmodel.NewsViewModel
import com.gustxvo.newsapp.ui.viewmodel.NewsViewModelFactory

class SavedNewsFragment : Fragment() {

    private var _binding: FragmentSearchNewsBinding? = null

    private val binding get() = _binding!!

    private lateinit var adapter: NewsAdapter

    private val viewModel: NewsViewModel by activityViewModels {
        NewsViewModelFactory(
            NewsRepository(ArticleDatabase(requireContext()))
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchNewsBinding.inflate(layoutInflater)
        adapter = NewsAdapter {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )
        }
        binding.rvSearchNews.adapter = adapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}