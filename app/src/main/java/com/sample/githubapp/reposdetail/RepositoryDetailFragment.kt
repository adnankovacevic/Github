package com.sample.githubapp.reposdetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.sample.githubapp.R
import com.sample.githubapp.databinding.FragmentRepositoryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepositoryDetailFragment : Fragment(R.layout.fragment_repository_detail) {

    private var binding: FragmentRepositoryDetailBinding? = null
    val args: RepositoryDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRepositoryDetailBinding.bind(view)

        binding!!.detailsNameTv.text = "Name: " + args.item.name
        binding!!.detailsDescriptionTv.text = "Description: " + args.item.description
        binding!!.ownerTv.text = "Owner: " + args.item.owner.login
        binding!!.languageTv.text = "Language: " + args.item.language
        binding!!.createdAtTv.text = "Created at: " + args.item.createdAt
        binding!!.updatedAtTv.text = "Updated at: " + args.item.updatedAt
        binding!!.pushedAt.text = "Pushed at: " + args.item.pushedAt

        binding!!.moreInfoBtn.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://github.com/" + args.item.fullName)
            })
        }
    }
}