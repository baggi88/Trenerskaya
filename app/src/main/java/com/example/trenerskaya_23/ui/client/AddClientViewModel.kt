package com.example.trenerskaya_23.ui.client

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.trenerskaya_23.data.dao.ClientDao
import com.example.trenerskaya_23.data.repository.ClientRepository
import com.example.trenerskaya_23.data.entity.ClientEntity
import com.example.trenerskaya_23.utils.PrefsManager
import kotlinx.coroutines.launch
import java.util.UUID
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddClientViewModel @Inject constructor(
    private val clientDao: ClientDao,
    private val clientRepository: ClientRepository,
    application: Application
) : AndroidViewModel(application) {
    private val _photoUri = MutableLiveData<Uri?>()
    val photoUri: LiveData<Uri?> = _photoUri

    private val _saveEnabled = MutableLiveData(false)
    val saveEnabled: LiveData<Boolean> = _saveEnabled

    private val _navigateBack = MutableLiveData(false)
    val navigateBack: LiveData<Boolean> = _navigateBack

    fun setPhoto(uri: Uri?) {
        _photoUri.value = uri
    }

    fun validateInputs(firstName: String, lastName: String) {
        _saveEnabled.value = firstName.isNotBlank() && lastName.isNotBlank()
    }

    fun saveClient(
        name: String,
        email: String,
        phone: String?,
        height: Double?,
        weight: Double?,
        notes: String?
    ) {
        viewModelScope.launch {
            val client = ClientEntity(
                name = name,
                email = email,
                phone = phone,
                photoUrl = null,
                height = height,
                weight = weight,
                goals = emptyList(),
                notes = notes,
                trainerId = 1L // TODO: Get actual trainer ID
            )
            clientDao.insertClient(client)
        }
    }

    fun resetNavigation() {
        _navigateBack.value = false
    }
} 