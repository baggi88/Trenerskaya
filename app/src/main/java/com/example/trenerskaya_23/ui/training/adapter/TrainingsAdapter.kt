package com.example.trenerskaya_23.ui.training.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trenerskaya_23.data.entity.TrainingEntity
import com.example.trenerskaya_23.databinding.ItemTrainingBinding
import java.time.format.DateTimeFormatter

class TrainingsAdapter(
    private val onTrainingClick: (TrainingEntity) -> Unit
) : ListAdapter<TrainingEntity, TrainingsAdapter.TrainingViewHolder>(TrainingDiffCallback()) {

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

    inner class TrainingViewHolder(
        private val binding: ItemTrainingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                // val position = bindingAdapterPosition // Закомментировано
                val position = layoutPosition // Используем layoutPosition как временное решение
                if (position != RecyclerView.NO_POSITION) {
                    onTrainingClick(getItem(position))
                }
            }
        }

        fun bind(training: TrainingEntity) {
            binding.apply {
                trainingName.text = training.name
                trainingDate.text = training.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                trainingDuration.text = "${training.duration} мин"
            }
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