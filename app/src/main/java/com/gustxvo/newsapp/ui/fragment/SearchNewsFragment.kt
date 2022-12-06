package com.gustxvo.newsapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gustxvo.newsapp.R
import com.gustxvo.newsapp.adapter.NewsAdapter
import com.gustxvo.newsapp.databinding.FragmentSearchNewsBinding
import com.gustxvo.newsapp.db.ArticleDatabase
import com.gustxvo.newsapp.repository.NewsRepository
import com.gustxvo.newsapp.ui.viewmodel.NewsViewModel
import com.gustxvo.newsapp.ui.viewmodel.NewsViewModelFactory
import com.gustxvo.newsapp.util.Resource
import com.gustxvo.newsapp.util.SEARCH_NEWS_TIME_DELAY
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "BreakingNewsFragment"

class SearchNewsFragment : Fragment() {

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
                R.id.action_searchNewsFragment_to_articleFragment,
                bundle
            )
        }
        binding.rvSearchNews.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var job: Job? = null
        binding.etSearch.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.searchNews(editable.toString())
                    }
                }
            }
        }

        viewModel.searchNews.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        adapter.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.d(TAG, "An error occurred: $message")
                    }
                }
                is Resource.Loading -> showProgressBar()
            }
        }
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}