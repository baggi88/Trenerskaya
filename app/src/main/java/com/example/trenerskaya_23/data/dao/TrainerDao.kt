package com.example.trenerskaya_23.data.dao

import androidx.room.*
import com.example.trenerskaya_23.data.entity.TrainerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainerDao {
    @Query("SELECT * FROM trainers")
    fun getAllTrainers(): Flow<List<TrainerEntity>>

    @Query("SELECT * FROM trainers WHERE id = :id")
    suspend fun getTrainerById(id: Long): TrainerEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrainer(trainer: TrainerEntity): Long

    @Update
    suspend fun updateTrainer(trainer: TrainerEntity)

    @Delete
    suspend fun deleteTrainer(trainer: TrainerEntity)

    @Query("SELECT * FROM trainers WHERE email = :email")
    suspend fun getTrainerByEmail(email: String): TrainerEntity?
} 