package com.example.trenerskaya_23.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trenerskaya_23.data.dao.TrainingDao
import com.example.trenerskaya_23.data.dao.ClientDao
import com.example.trenerskaya_23.data.entity.TrainingEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TrainingListViewModel @Inject constructor(
    private val trainingDao: TrainingDao,
    private val clientDao: ClientDao
) : ViewModel() {

    private val _trainings = MutableLiveData<List<TrainingEntity>>()
    val trainings: LiveData<List<TrainingEntity>> = _trainings

    init {
        loadTrainings()
    }

    private fun loadTrainings() {
        viewModelScope.launch {
            val trainerId = 1L // TODO: Get actual trainer ID
            _trainings.value = trainingDao.getTrainingsByTrainerIdAndDate(trainerId, LocalDate.now())
        }
    }

    fun setSelectedDate(date: LocalDate) {
        viewModelScope.launch {
            val trainerId = 1L // TODO: Get actual trainer ID
            _trainings.value = trainingDao.getTrainingsByTrainerIdAndDate(trainerId, date)
        }
    }

    fun refreshData() {
        loadTrainings()
    }
} 