package com.example.trenerskaya_23.ui.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trenerskaya_23.data.dao.TrainingDao
import com.example.trenerskaya_23.data.dao.ExerciseDao
import com.example.trenerskaya_23.data.entity.TrainingEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val trainingDao: TrainingDao,
    private val exerciseDao: ExerciseDao
) : ViewModel() {

    private val _trainings = MutableStateFlow<List<TrainingEntity>>(emptyList())
    val trainings: StateFlow<List<TrainingEntity>> = _trainings

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate

    private val trainerId = 1L

    init {
        loadTrainings(trainerId, _selectedDate.value)
    }

    fun setSelectedDate(date: LocalDate) {
        _selectedDate.value = date
        loadTrainings(trainerId, date)
    }

    private fun loadTrainings(trainerId: Long, date: LocalDate) {
        viewModelScope.launch {
            try {
                _trainings.value = trainingDao.getTrainingsByTrainerIdAndDate(trainerId, date)
            } catch(e: Exception) {
                _trainings.value = emptyList()
            }
        }
    }
} 