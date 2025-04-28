package com.example.trenerskaya_23.ui.client

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.trenerskaya_23.databinding.FragmentAddClientBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddClientFragment : Fragment() {

    private var _binding: FragmentAddClientBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddClientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddClientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeViewModel()
    }

    private fun setupViews() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.nameInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateName()
            }
        }

        binding.emailInput.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateEmail()
            }
        }

        binding.saveButton.setOnClickListener {
            if (validateForm()) {
                saveClient()
            }
        }
    }

    private fun validateName(): Boolean {
        val name = binding.nameInput.text.toString()
        return if (name.isBlank()) {
            binding.nameInput.error = "Введите имя"
            false
        } else {
            binding.nameInput.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        val email = binding.emailInput.text.toString()
        return if (email.isBlank()) {
            binding.emailInput.error = "Введите email"
            false
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailInput.error = "Введите корректный email"
            false
        } else {
            binding.emailInput.error = null
            true
        }
    }

    private fun validateForm(): Boolean {
        return validateName() && validateEmail()
    }

    private fun saveClient() {
        val name = binding.nameInput.text.toString()
        val email = binding.emailInput.text.toString()
        val phone = binding.phoneInput.text.toString()
        val height = binding.heightInput.text.toString().toDoubleOrNull()
        val weight = binding.weightInput.text.toString().toDoubleOrNull()
        val notes = binding.notesInput.text.toString()

        viewModel.saveClient(name, email, phone, height, weight, notes)
        findNavController().navigateUp()
    }

    private fun observeViewModel() {
        viewModel.saveEnabled.observe(viewLifecycleOwner) { enabled ->
            binding.saveButton.isEnabled = enabled
        }

        viewModel.navigateBack.observe(viewLifecycleOwner) { shouldNavigate ->
            if (shouldNavigate) {
                findNavController().navigateUp()
                viewModel.resetNavigation()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 