package com.example.trenerskaya_23.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.trenerskaya_23.data.dao.ClientDao
import com.example.trenerskaya_23.data.dao.TrainingDao
import com.example.trenerskaya_23.data.dao.ExerciseDao
import com.example.trenerskaya_23.data.dao.TrainerDao
import com.example.trenerskaya_23.data.entity.ClientEntity
import com.example.trenerskaya_23.data.entity.TrainerEntity
import com.example.trenerskaya_23.data.entity.TrainingEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.pow

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clientDao: ClientDao,
    private val trainingDao: TrainingDao,
    private val exerciseDao: ExerciseDao,
    private val trainerDao: TrainerDao
) : ViewModel() {

    private val currentTrainerIdFlow = MutableLiveData(1L)

    val clients: LiveData<List<ClientEntity>> = currentTrainerIdFlow.asFlow()
        .flatMapLatest { trainerId: Long ->
            clientDao.getClientsByTrainerId(trainerId)
        }.asLiveData()

    private val _trainings = MutableLiveData<List<TrainingEntity>>()
    val trainings: LiveData<List<TrainingEntity>> = _trainings

    private val _selectedDate = MutableLiveData(LocalDate.now())
    val selectedDate: LiveData<LocalDate> = _selectedDate

    private val _trainer = MutableLiveData<TrainerEntity?>()
    val trainer: LiveData<TrainerEntity?> = _trainer

    private val _bmiResult = MutableLiveData<String?>()
    val bmiResult: LiveData<String?> = _bmiResult

    init {
        loadTrainerData()
        loadTrainingsForDate(selectedDate.value ?: LocalDate.now())
    }

    private fun loadTrainerData() {
        viewModelScope.launch {
            val trainerId = currentTrainerIdFlow.value ?: 1L
            try {
                _trainer.value = trainerDao.getTrainerById(trainerId)
            } catch (e: Exception) {
                _trainer.value = null
            }
        }
    }

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
        loadTrainingsForDate(date)
    }

    private fun loadTrainingsForDate(date: LocalDate) {
        viewModelScope.launch {
            val trainerId = currentTrainerIdFlow.value ?: 1L
            try {
                _trainings.value = trainingDao.getTrainingsByTrainerIdAndDate(trainerId, date)
            } catch (e: Exception) {
                _trainings.value = emptyList()
            }
        }
    }

    fun calculateBMI(heightCm: Int, weightKg: Double) {
        if (heightCm <= 0 || weightKg <= 0) {
            _bmiResult.value = "Введите корректные рост и вес"
            return
        }
        val heightM = heightCm / 100.0
        val bmi = weightKg / heightM.pow(2)
        val bmiFormatted = String.format("%.1f", bmi)

        val resultText = when {
            bmi < 18.5 -> "Недостаточный вес ($bmiFormatted)"
            bmi < 25 -> "Нормальный вес ($bmiFormatted)"
            bmi < 30 -> "Избыточный вес ($bmiFormatted)"
            else -> "Ожирение ($bmiFormatted)"
        }
        _bmiResult.value = resultText
    }

    fun refreshTrainings() {
        loadTrainingsForDate(selectedDate.value ?: LocalDate.now())
    }

    fun refreshAllData() {
        loadTrainerData()
        refreshTrainings()
    }
}