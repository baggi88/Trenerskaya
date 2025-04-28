package com.example.trenerskaya_23.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trenerskaya_23.data.entity.ClientEntity
import com.example.trenerskaya_23.databinding.ItemClientBinding

class ClientAdapter : ListAdapter<ClientEntity, ClientAdapter.ClientViewHolder>(ClientDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val binding = ItemClientBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ClientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ClientViewHolder(
        private val binding: ItemClientBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(client: ClientEntity) {
            // binding.clientNameText.text = client.name
            // binding.clientGoalsText.text = client.goals?.joinToString(", ") ?: "Нет целей"
        }
    }

    private class ClientDiffCallback : DiffUtil.ItemCallback<ClientEntity>() {
        override fun areItemsTheSame(oldItem: ClientEntity, newItem: ClientEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ClientEntity, newItem: ClientEntity): Boolean {
            return oldItem == newItem
        }
    }
} 