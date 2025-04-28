package com.example.trenerskaya_23.ui.training.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.trenerskaya_23.databinding.FragmentViewTrainingBinding
import com.example.trenerskaya_23.ui.training.adapter.ExerciseAdapter
import com.example.trenerskaya_23.ui.training.dialog.AddExerciseDialog
import com.example.trenerskaya_23.ui.training.viewmodel.ViewTrainingViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class ViewTrainingFragment : Fragment() {
    private var _binding: FragmentViewTrainingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ViewTrainingViewModel by viewModels()
    private val args: ViewTrainingFragmentArgs by navArgs()

    private val exerciseAdapter = ExerciseAdapter { exercise ->
        viewModel.deleteExercise(exercise)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTrainingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exercisesRecyclerView.adapter = exerciseAdapter

        binding.addExerciseButton.setOnClickListener {
            showAddExerciseDialog()
        }

        binding.deleteTrainingButton.setOnClickListener {
            viewModel.deleteTraining()
            findNavController().navigateUp()
        }

        viewModel.training.observe(viewLifecycleOwner) { training ->
            training?.let {
                binding.apply {
                    trainingName.text = it.name
                    trainingDate.text = it.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
                    trainingDuration.text = "${it.duration} мин"
                    trainingNotes.text = it.notes ?: "Нет заметок"
                }
            }
        }

        viewModel.exercises.observe(viewLifecycleOwner) { exercises ->
            exerciseAdapter.submitList(exercises)
        }

        viewModel.loadTraining(args.trainingId)
    }

    private fun showAddExerciseDialog() {
        AddExerciseDialog.newInstance(args.trainingId).apply {
            onExerciseAdded = { exercise ->
                viewModel.addExercise(exercise)
            }
        }.show(childFragmentManager, "AddExerciseDialog")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 