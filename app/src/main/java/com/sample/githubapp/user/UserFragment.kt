package com.sample.githubapp.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sample.githubapp.R
import com.sample.githubapp.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment(R.layout.fragment_user) {

    private var binding: FragmentUserBinding? = null

    val args: UserFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserBinding.bind(view)

        binding!!.userNameTv.text = args.item.owner.login
        Glide.with(requireActivity()).load(args.item.owner.avatarUrl).into(binding!!.userAvatarIv)


        binding?.goToProfileBtn?.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(args.item.owner.htmlUrl)
            })
        }
    }

}