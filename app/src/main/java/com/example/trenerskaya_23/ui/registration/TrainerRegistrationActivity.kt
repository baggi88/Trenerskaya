package com.example.trenerskaya_23.ui.registration

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.trenerskaya_23.MainActivity
import com.example.trenerskaya_23.R
import com.example.trenerskaya_23.data.model.Trainer
import com.example.trenerskaya_23.databinding.ActivityTrainerRegistrationBinding
import com.google.android.material.textfield.TextInputEditText

class TrainerRegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainerRegistrationBinding
    private val viewModel: TrainerRegistrationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainerRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupRegistrationButton()
        observeViewModel()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupRegistrationButton() {
        binding.registerButton.setOnClickListener {
            viewModel.registerTrainer(
                firstName = binding.firstName.text.toString().trim(),
                lastName = binding.lastName.text.toString().trim(),
                height = binding.height.text.toString().trim(),
                weight = binding.weight.text.toString().trim(),
                experience = binding.experience.text.toString().trim(),
                achievements = binding.achievements.text.toString().trim()
            )
        }
    }

    private fun observeViewModel() {
        viewModel.registrationState.observe(this) { state ->
            when (state) {
                is TrainerRegistrationViewModel.RegistrationState.Success -> {
                    // Сохраняем ID тренера для дальнейшего использования
                    getSharedPreferences("trainer_prefs", MODE_PRIVATE)
                        .edit()
                        .putString("current_trainer_id", state.trainerId)
                        .apply()

                    // Переходим на главный экран
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is TrainerRegistrationViewModel.RegistrationState.Error -> {
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.validationState.observe(this) { state ->
            when (state) {
                is TrainerRegistrationViewModel.ValidationState.Error -> {
                    binding.firstNameLayout.error = state.message
                }
            }
        }
    }

    private fun TextInputEditText.toIntOrNull(): Int? {
        return text?.toString()?.trim()?.takeIf { it.isNotEmpty() }?.toIntOrNull()
    }

    private fun TextInputEditText.toDoubleOrNull(): Double? {
        return text?.toString()?.trim()?.takeIf { it.isNotEmpty() }?.toDoubleOrNull()
    }
} 