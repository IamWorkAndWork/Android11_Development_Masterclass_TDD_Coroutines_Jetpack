package com.example.android11developmentmasterclass.presenter.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android11developmentmasterclass.databinding.ItemPlaylistBinding
import com.example.android11developmentmasterclass.domain.model.PlaylistModel

class PlaylistAdapter(
    private val listener: (String) -> Unit
) : ListAdapter<PlaylistModel, RecyclerView.ViewHolder>(diffPlaylistModel) {

    companion object {

        val diffPlaylistModel = object : DiffUtil.ItemCallback<PlaylistModel>() {
            override fun areItemsTheSame(oldItem: PlaylistModel, newItem: PlaylistModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PlaylistModel,
                newItem: PlaylistModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPlaylistBinding.inflate(inflater, parent, false)
        return PlaylistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PlaylistViewHolder) {
            holder.bind(getItem(position))
        }
    }

    inner class PlaylistViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val item = getItem(bindingAdapterPosition)
                listener.invoke(item.id)
            }
        }

        fun bind(item: PlaylistModel) = with(binding) {
            playlistName.text = item.name
            playlistCategory.text = item.category
            playlistImage.setImageResource(item.image)
        }

    }
}