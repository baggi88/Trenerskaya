package com.example.trenerskaya_23.data.dao

import androidx.room.*
import com.example.trenerskaya_23.data.entity.ClientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {
    @Query("SELECT * FROM clients")
    fun getAllClients(): Flow<List<ClientEntity>>

    @Query("SELECT * FROM clients WHERE trainerId = :trainerId")
    fun getClientsByTrainerId(trainerId: Long): Flow<List<ClientEntity>>

    @Query("SELECT * FROM clients WHERE id = :id")
    suspend fun getClientById(id: Long): ClientEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClient(client: ClientEntity): Long

    @Update
    suspend fun updateClient(client: ClientEntity)

    @Delete
    suspend fun deleteClient(client: ClientEntity)

    @Query("SELECT COUNT(*) FROM clients WHERE trainerId = :trainerId")
    fun getClientCount(trainerId: Long): Flow<Int>

    @Query("SELECT * FROM clients WHERE trainerId = :trainerId AND name LIKE '%' || :query || '%'")
    fun searchClients(trainerId: Long, query: String): Flow<List<ClientEntity>>
} 