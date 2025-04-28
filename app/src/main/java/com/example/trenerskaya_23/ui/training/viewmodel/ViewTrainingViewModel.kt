package com.example.trenerskaya_23.ui.training.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trenerskaya_23.data.entity.ExerciseEntity
import com.example.trenerskaya_23.data.entity.TrainingEntity
import com.example.trenerskaya_23.data.repository.TrainingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewTrainingViewModel @Inject constructor(
    private val trainingRepository: TrainingRepository
) : ViewModel() {

    private val _training = MutableLiveData<TrainingEntity>()
    val training: LiveData<TrainingEntity> = _training

    private val _exercises = MutableLiveData<List<ExerciseEntity>>()
    val exercises: LiveData<List<ExerciseEntity>> = _exercises

    private val _navigateBack = MutableLiveData<Boolean>()
    val navigateBack: LiveData<Boolean> = _navigateBack

    private val _navigateToEdit = MutableLiveData<Long>()
    val navigateToEdit: LiveData<Long> = _navigateToEdit

    fun loadTraining(trainingId: Long) {
        viewModelScope.launch {
            _training.value = trainingRepository.getTrainingById(trainingId)
            _exercises.value = trainingRepository.getExercisesForTraining(trainingId)
        }
    }

    fun addExercise(exercise: ExerciseEntity) {
        viewModelScope.launch {
            trainingRepository.insertExercise(exercise)
            loadTraining(exercise.trainingId)
        }
    }

    fun deleteExercise(exercise: ExerciseEntity) {
        viewModelScope.launch {
            trainingRepository.deleteExercise(exercise)
            loadTraining(exercise.trainingId)
        }
    }

    fun deleteTraining() {
        viewModelScope.launch {
            _training.value?.let { training ->
                trainingRepository.deleteTraining(training)
            }
        }
    }

    fun onEditClick() {
        _training.value?.let { training ->
            _navigateToEdit.value = training.id
        }
    }

    fun onNavigateBackComplete() {
        _navigateBack.value = false
    }

    fun onNavigateToEditComplete() {
        _navigateToEdit.value = null
    }
} 