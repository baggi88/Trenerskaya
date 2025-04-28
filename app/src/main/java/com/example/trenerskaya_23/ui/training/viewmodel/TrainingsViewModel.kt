package com.example.trenerskaya_23.ui.training.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trenerskaya_23.data.entity.TrainingEntity
import com.example.trenerskaya_23.data.repository.TrainingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainingsViewModel @Inject constructor(
    private val trainingRepository: TrainingRepository
) : ViewModel() {

    private val _trainings = MutableLiveData<List<TrainingEntity>>()
    val trainings: LiveData<List<TrainingEntity>> = _trainings

    init {
        loadTrainings()
    }

    fun loadTrainings() {
        viewModelScope.launch {
            _trainings.value = trainingRepository.getAllTrainings()
        }
    }

    fun refreshData() {
        loadTrainings()
    }
} 