package com.example.trenerskaya_23.ui.training.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trenerskaya_23.data.dao.ExerciseDao
import com.example.trenerskaya_23.data.dao.TrainingDao
import com.example.trenerskaya_23.data.model.Exercise
import com.example.trenerskaya_23.data.model.Training
import com.example.trenerskaya_23.data.entity.TrainingEntity
import com.example.trenerskaya_23.data.repository.TrainingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AddTrainingViewModel @Inject constructor(
    private val trainingDao: TrainingDao,
    private val exerciseDao: ExerciseDao,
    private val trainingRepository: TrainingRepository
) : ViewModel() {

    private val _exercises = MutableStateFlow<List<Exercise>>(emptyList())
    val exercises: StateFlow<List<Exercise>> = _exercises

    private val _trainingName = MutableLiveData<String>()
    val trainingName: LiveData<String> = _trainingName

    private val _trainingDate = MutableLiveData<Date>()
    val trainingDate: LiveData<Date> = _trainingDate

    private val _trainingTime = MutableLiveData<String>()
    val trainingTime: LiveData<String> = _trainingTime

    private val _navigateBack = MutableLiveData<Boolean>()
    val navigateBack: LiveData<Boolean> = _navigateBack

    private var trainingId: Long = 0
    private var isEditMode = false

    /* // Закомментировано
    fun loadTraining(trainingId: Long) {
        if (trainingId == 0L) return
        
        this.trainingId = trainingId
        isEditMode = true

        viewModelScope.launch {
            trainingDao.getTrainingById(trainingId)?.let { training ->
                _trainingName.value = training.name
                _trainingDate.value = training.date
                _trainingTime.value = training.time
            }
            exerciseDao.getExercisesByTrainingId(trainingId).collect { exercises ->
                _exercises.value = exercises
            }
        }
    }
    */

    fun setTrainingName(name: String) {
        _trainingName.value = name
    }

    fun setTrainingDate(date: Date) {
        _trainingDate.value = date
    }

    fun setTrainingTime(time: String) {
        _trainingTime.value = time
    }

    /* // Закомментировано
    fun addExercise(exercise: Exercise) {
        val currentList = _exercises.value.toMutableList()
        currentList.add(exercise)
        _exercises.value = currentList
    }
    */

    /* // Закомментировано
    fun deleteExercise(exercise: Exercise) {
        val currentList = _exercises.value.toMutableList()
        currentList.remove(exercise)
        _exercises.value = currentList

        if (isEditMode && exercise.id != 0L) {
            viewModelScope.launch {
                exerciseDao.delete(exercise)
            }
        }
    }
    */

    /* // Закомментировано
    fun saveTraining() {
        if (_trainingName.value.isNullOrBlank() || 
            _trainingDate.value == null || 
            _trainingTime.value.isNullOrBlank()) {
            return
        }

        viewModelScope.launch {
            val training = Training(
                id = if (isEditMode) trainingId else 0,
                name = _trainingName.value!!,
                date = _trainingDate.value!!,
                time = _trainingTime.value!!
            )

            trainingId = if (isEditMode) {
                trainingDao.update(training)
                training.id
            } else {
                trainingDao.insert(training)
            }

            // Сохраняем упражнения
            _exercises.value.forEach { exercise ->
                val exerciseWithTrainingId = exercise.copy(
                    id = if (isEditMode) exercise.id else 0,
                    trainingId = trainingId
                )
                if (isEditMode && exercise.id != 0L) {
                    exerciseDao.update(exerciseWithTrainingId)
                } else {
                    exerciseDao.insert(exerciseWithTrainingId)
                }
            }

            _navigateBack.value = true
        }
    }
    */

    fun onNavigateBackComplete() {
        _navigateBack.value = false
    }

    /* // Закомментировано
    fun saveTraining(name: String, dateString: String, duration: Int, notes: String) {
        viewModelScope.launch {
            val date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            val training = TrainingEntity(
                name = name,
                date = date,
                duration = duration,
                notes = notes,
                trainerId = 1L, // TODO: Получить ID текущего тренера
                clientId = 1L  // TODO: Получить ID текущего клиента
            )
            trainingRepository.insertTraining(training)
        }
    }
    */
} 