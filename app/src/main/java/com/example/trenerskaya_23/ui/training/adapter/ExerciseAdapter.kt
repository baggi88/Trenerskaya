package com.example.trenerskaya_23.ui.training.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.trenerskaya_23.data.entity.ExerciseEntity
import com.example.trenerskaya_23.databinding.ItemExerciseBinding

class ExerciseAdapter(
    private val onExerciseClick: (ExerciseEntity) -> Unit
) : ListAdapter<ExerciseEntity, ExerciseAdapter.ExerciseViewHolder>(ExerciseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseViewHolder {
        val binding = ItemExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ExerciseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ExerciseViewHolder(
        private val binding: ItemExerciseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                // val position = bindingAdapterPosition // Закомментировано
                val position = layoutPosition // Используем layoutPosition как временное решение
                if (position != RecyclerView.NO_POSITION) {
                    onExerciseClick(getItem(position))
                }
            }
        }

        fun bind(exercise: ExerciseEntity) {
            binding.apply {
                exerciseName.text = exercise.name
                exerciseSets.text = "${exercise.sets} подхода"
                exerciseReps.text = "${exercise.reps} повторений"
                exerciseWeight.text = exercise.weight?.let { "$it кг" } ?: "Без веса"
                exerciseNotes.text = exercise.notes ?: "Нет заметок"
            }
        }
    }

    private class ExerciseDiffCallback : DiffUtil.ItemCallback<ExerciseEntity>() {
        override fun areItemsTheSame(oldItem: ExerciseEntity, newItem: ExerciseEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ExerciseEntity, newItem: ExerciseEntity): Boolean {
            return oldItem == newItem
        }
    }
} 