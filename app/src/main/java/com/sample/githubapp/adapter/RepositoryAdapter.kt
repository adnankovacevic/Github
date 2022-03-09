package com.sample.githubapp.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.githubapp.data.model.Item
import com.sample.githubapp.databinding.RepositoryItemBinding

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {


    private val repositoryList: MutableList<Item> = mutableListOf()



    var onItemClickListener: ClickListener? = null

    fun updateList(repos: List<Item>) {
        repositoryList.clear()
        repositoryList.addAll(repos)
        notifyDataSetChanged()
    }

    inner class RepositoryViewHolder(private val binding: RepositoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.repositoryLayout.setOnClickListener {

                onItemClickListener?.onItemClicked(repositoryList[layoutPosition])
            }

            binding.avatarIv.setOnClickListener {
                onItemClickListener?.onUserClicked(repositoryList[layoutPosition])
            }
        }

        fun bind(data: Item) {
            binding.nameTv.text = data.fullName
            binding.descriptionTv.text = data.description
             binding.forksTv.text = "Forks:"+ data.forksCount.toString()
            binding.watchersTv.text ="Watchers:" + data.watchersCount.toString()
             binding.issuesTv.text = "Issues:" + data.openIssuesCount.toString()

            Glide.with(itemView.context)
                .load(data.owner.avatarUrl)
                .into(binding.avatarIv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RepositoryItemBinding.inflate(layoutInflater, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repositoryList[position])

        Log.d("RecyclerView", "Number of items:${repositoryList[position]}")
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    interface ClickListener{
        fun onItemClicked(repository:Item)
        fun onUserClicked(repository: Item)
    }

}