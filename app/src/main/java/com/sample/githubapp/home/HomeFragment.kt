package com.sample.githubapp.home

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.githubapp.R
import com.sample.githubapp.adapter.RepositoryAdapter
import com.sample.githubapp.data.model.Item
import com.sample.githubapp.databinding.FragmentHomeBinding
import com.sample.githubapp.util.selected
import dagger.hilt.android.AndroidEntryPoint
import debounce

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var binding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by activityViewModels()
    private val repositoryAdapter = RepositoryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding?.reposRv?.layoutManager = LinearLayoutManager(activity)

        setUi()
        observe()
        navigateTo()
    }

    fun navigateTo() {

        repositoryAdapter.onItemClickListener = object : RepositoryAdapter.ClickListener {
            override fun onItemClicked(repository: Item) {

                val action =
                    HomeFragmentDirections.actionHomeFragmentToRepositoryDetailFragment(repository)
                findNavController().navigate(action)
            }

            override fun onUserClicked(repository: Item) {
                val action = HomeFragmentDirections.actionHomeFragmentToUserFragment(repository)
                findNavController().navigate(action)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun observe() {
        homeViewModel.repositories.observe(viewLifecycleOwner) { results ->

            Log.d("HomeFragment", "$results")
            if (results != null) {
                repositoryAdapter.updateList(results)
            }
        }
    }

    private fun setUi() {

        val onQueryChange: (String) -> Unit =
            debounce(300L, viewLifecycleOwner.lifecycleScope, this::onQueryChanged)
        binding?.etSearch?.doOnTextChanged { text, start, before, count -> onQueryChange(text.toString()) }

        binding?.sortingSpinner?.selected { loadData() }
    }

    private fun onQueryChanged(query: String) {
        Log.d("Query", query)
        loadData()
    }

    private fun loadData() {

        val searchQuery = binding?.etSearch?.text.toString()
        val sortQuery = binding?.sortingSpinner?.selectedItem.toString().lowercase()
        if (searchQuery.isNotBlank()) {
            binding?.reposRv?.adapter = repositoryAdapter
            homeViewModel.getRepositories(searchQuery, sortQuery)

        }
    }
}