package com.example.trenerskaya_23.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.trenerskaya_23.data.dao.TrainerDao
import com.example.trenerskaya_23.data.entity.TrainerEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrainerRegistrationViewModel @Inject constructor(
    private val trainerDao: TrainerDao // Получаем Dao через Hilt
) : ViewModel() { // Убрали AndroidViewModel

    private val _registrationState = MutableLiveData<RegistrationState>()
    val registrationState: LiveData<RegistrationState> = _registrationState

    private val _validationState = MutableLiveData<ValidationState>()
    val validationState: LiveData<ValidationState> = _validationState

    fun registerTrainer(
        firstName: String,
        lastName: String,
        height: String,
        weight: String,
        experience: String,
        achievements: String
    ) {
        if (!validateInput(firstName)) {
            _validationState.value = ValidationState.Error("Введите имя")
            return
        }

        viewModelScope.launch {
            try {
                // Создаем TrainerEntity
                val newTrainer = TrainerEntity(
                    name = "$firstName $lastName", // Используем name, как в Entity
                    email = "", // TODO: Добавить email
                    phone = "", // TODO: Добавить phone
                    photoUrl = null,
                    notes = "Рост: ${height}, Вес: ${weight}, Опыт: ${experience}, Достижения: $achievements"
                )
                // Вставляем через Dao
                val trainerId = trainerDao.insertTrainer(newTrainer)
                _registrationState.value = RegistrationState.Success(trainerId.toString()) // Возвращаем ID как String
            } catch (e: Exception) {
                _registrationState.value = RegistrationState.Error(e.message ?: "Ошибка регистрации")
            }
        }
    }

    private fun validateInput(firstName: String): Boolean {
        return firstName.isNotBlank()
    }

    sealed class RegistrationState {
        data class Success(val trainerId: String) : RegistrationState()
        data class Error(val message: String) : RegistrationState()
    }

    sealed class ValidationState {
        data class Error(val message: String) : ValidationState()
    }
} 