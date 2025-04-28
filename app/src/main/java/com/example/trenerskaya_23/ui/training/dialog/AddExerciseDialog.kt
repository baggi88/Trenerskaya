package com.example.trenerskaya_23.ui.training.dialog

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.trenerskaya_23.data.entity.ExerciseEntity
import com.example.trenerskaya_23.databinding.DialogAddExerciseBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AddExerciseDialog : DialogFragment() {
    private var _binding: DialogAddExerciseBinding? = null
    private val binding get() = _binding!!
    internal var onExerciseAdded: ((ExerciseEntity) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddExerciseBinding.inflate(layoutInflater)

        return MaterialAlertDialogBuilder(requireContext())
            .setTitle("Добавить упражнение")
            .setView(binding.root)
            .setPositiveButton("Добавить") { _, _ ->
                val name = binding.nameInput.text.toString()
                val sets = binding.setsInput.text.toString().toIntOrNull() ?: 0
                val reps = binding.repsInput.text.toString().toIntOrNull() ?: 0
                val weight = binding.weightInput.text.toString().toFloatOrNull()
                val notes = binding.notesInput.text.toString()

                if (name.isBlank() || sets == 0 || reps == 0) {
                    return@setPositiveButton
                }

                val exercise = ExerciseEntity(
                    trainingId = arguments?.getLong(ARG_TRAINING_ID) ?: 0L,
                    name = name,
                    sets = sets,
                    reps = reps,
                    weight = weight,
                    notes = notes
                )

                onExerciseAdded?.invoke(exercise)
                dismiss()
            }
            .setNegativeButton("Отмена") { _, _ ->
                dismiss()
            }
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TRAINING_ID = "training_id"

        fun newInstance(trainingId: Long): AddExerciseDialog {
            return AddExerciseDialog().apply {
                arguments = Bundle().apply {
                    putLong(ARG_TRAINING_ID, trainingId)
                }
            }
        }
    }
} 