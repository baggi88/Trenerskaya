package com.example.trenerskaya_23.ui.training.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.trenerskaya_23.databinding.FragmentAddTrainingBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@AndroidEntryPoint
class AddTrainingFragment : Fragment() {
    private var _binding: FragmentAddTrainingBinding? = null
    private val binding get() = _binding!!
    // private val viewModel: AddTrainingViewModel by viewModels() // Закомментировано

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dateButton.setOnClickListener {
            showDatePicker()
        }

        binding.saveButton.setOnClickListener {
            saveTraining()
        }
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Выберите дату")
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            val date = Instant.ofEpochMilli(selection)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
            binding.dateButton.text = date.format(java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        }

        datePicker.show(parentFragmentManager, "date_picker")
    }

    private fun saveTraining() {
        val name = binding.nameInput.text.toString()
        val date = binding.dateButton.text.toString()
        val duration = binding.durationInput.text.toString().toIntOrNull() ?: 0
        val notes = binding.notesInput.text.toString()

        if (name.isBlank() || date == "Выберите дату") {
            return
        }

        // viewModel.saveTraining(name, date, duration, notes) // Закомментировано
        findNavController().navigateUp()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 