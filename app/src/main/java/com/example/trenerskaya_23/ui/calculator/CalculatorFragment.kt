package com.example.trenerskaya_23.ui.calculator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.trenerskaya_23.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        setupActivityLevelDropdown()
        setupInputListeners()
        setupCalculateButton()
    }

    private fun setupActivityLevelDropdown() {
        val activities = CalculatorViewModel.ActivityLevel.values().map { it.title }
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, activities)
        binding.activityInput.setAdapter(adapter)
    }

    private fun setupInputListeners() {
        binding.weightInput.doAfterTextChanged { calculateBMI() }
        binding.heightInput.doAfterTextChanged { calculateBMI() }
    }

    private fun setupCalculateButton() {
        binding.calculateButton.setOnClickListener {
            val weight = binding.weightInput.text.toString().toDoubleOrNull() ?: return@setOnClickListener
            val height = binding.heightInput.text.toString().toDoubleOrNull() ?: return@setOnClickListener
            val age = binding.ageInput.text.toString().toIntOrNull() ?: return@setOnClickListener
            val activityTitle = binding.activityInput.text.toString()
            val activityLevel = CalculatorViewModel.ActivityLevel.values()
                .find { it.title == activityTitle } ?: return@setOnClickListener

            viewModel.calculateMacros(
                weight = weight,
                height = height,
                age = age,
                isMale = binding.maleRadio.isChecked,
                activityLevel = activityLevel
            )
        }
    }

    private fun calculateBMI() {
        val weight = binding.weightInput.text.toString().toDoubleOrNull()
        val height = binding.heightInput.text.toString().toDoubleOrNull()

        if (weight != null && height != null && height > 0) {
            viewModel.calculateBMI(weight, height)
        }
    }

    private fun observeViewModel() {
        viewModel.bmiResult.observe(viewLifecycleOwner) { bmi ->
            binding.bmiResult.text = String.format("%.1f", bmi)
        }

        viewModel.bmiCategory.observe(viewLifecycleOwner) { category ->
            binding.bmiCategory.text = category
        }

        viewModel.calories.observe(viewLifecycleOwner) { calories ->
            binding.caloriesResult.text = "$calories ккал"
        }

        viewModel.protein.observe(viewLifecycleOwner) { protein ->
            binding.proteinResult.text = "Белки\n${protein}г"
        }

        viewModel.fats.observe(viewLifecycleOwner) { fats ->
            binding.fatsResult.text = "Жиры\n${fats}г"
        }

        viewModel.carbs.observe(viewLifecycleOwner) { carbs ->
            binding.carbsResult.text = "Углеводы\n${carbs}г"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 