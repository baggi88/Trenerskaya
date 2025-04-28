package com.example.trenerskaya_23.data.repository

import com.example.trenerskaya_23.data.dao.ClientDao
import com.example.trenerskaya_23.data.entity.ClientEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClientRepository @Inject constructor(
    private val clientDao: ClientDao
) {
    fun getAllClients(): Flow<List<ClientEntity>> = clientDao.getAllClients()

    fun getClientsByTrainerId(trainerId: Long): Flow<List<ClientEntity>> =
        clientDao.getClientsByTrainerId(trainerId)

    suspend fun getClientById(id: Long): ClientEntity? = clientDao.getClientById(id)

    suspend fun insertClient(client: ClientEntity): Long = clientDao.insertClient(client)

    suspend fun updateClient(client: ClientEntity) = clientDao.updateClient(client)

    suspend fun deleteClient(client: ClientEntity) = clientDao.deleteClient(client)

    fun searchClients(trainerId: Long, query: String): Flow<List<ClientEntity>> =
        clientDao.searchClients(trainerId, query)
} 