package com.trufla.task.app.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.trufla.task.R
import com.trufla.task.app.domain.LibraryEntity
import com.trufla.task.databinding.ItemLibraryBinding

class LibrariesAdapter :
    ListAdapter<LibraryEntity, LibrariesAdapter.LibraryItemViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            LibraryItemViewHolder {
        val binding = ItemLibraryBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibraryItemViewHolder, position: Int) {
        holder.bindData(getItem(position), position)
    }


    inner class LibraryItemViewHolder(private val binding: ItemLibraryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(libraryEntity: LibraryEntity, position: Int) {
            binding.tvLibraryName.text = libraryEntity.libraryName
            binding.tvLicenseType.text = position.toString()
            binding.tvWatchers.text= itemView.context.getString(R.string.no_of_watchers, libraryEntity.watchersCount)
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<LibraryEntity>() {
        override fun areItemsTheSame(
            oldItem: LibraryEntity,
            newItem: LibraryEntity
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: LibraryEntity,
            newItem: LibraryEntity
        ): Boolean {
            return oldItem == newItem
        }
    }
}