package com.example.trenerskaya_23.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trenerskaya_23.data.repository.TrainingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val trainingRepository: TrainingRepository
) : ViewModel() {

    fun clearAllData() {
        viewModelScope.launch {
            trainingRepository.deleteAllTrainings()
        }
    }
} 