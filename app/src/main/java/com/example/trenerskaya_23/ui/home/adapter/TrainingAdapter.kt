package com.example.trenerskaya_23.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trenerskaya_23.data.entity.TrainingEntity
import com.example.trenerskaya_23.databinding.ItemTrainingBinding
import java.time.format.DateTimeFormatter

class TrainingAdapter : ListAdapter<TrainingEntity, TrainingAdapter.TrainingViewHolder>(TrainingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val binding = ItemTrainingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TrainingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TrainingViewHolder(
        private val binding: ItemTrainingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        fun bind(training: TrainingEntity) {
            // binding.trainingNameText.text = training.name
            // binding.trainingDateText.text = training.date.format(dateFormatter)
            // binding.trainingDurationText.text = "${training.duration} мин"
            // binding.trainingNotesText.text = training.notes ?: "Нет заметок"
        }
    }

    private class TrainingDiffCallback : DiffUtil.ItemCallback<TrainingEntity>() {
        override fun areItemsTheSame(oldItem: TrainingEntity, newItem: TrainingEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TrainingEntity, newItem: TrainingEntity): Boolean {
            return oldItem == newItem
        }
    }
} 