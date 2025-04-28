package com.example.trenerskaya_23.data.repository

import com.example.trenerskaya_23.data.dao.TrainerDao
import com.example.trenerskaya_23.data.entity.TrainerEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainerRepository @Inject constructor(
    private val trainerDao: TrainerDao
) {
    fun getAllTrainers(): Flow<List<TrainerEntity>> = trainerDao.getAllTrainers()

    suspend fun getTrainerById(id: Long): TrainerEntity? = trainerDao.getTrainerById(id)

    suspend fun insertTrainer(trainer: TrainerEntity): Long = trainerDao.insertTrainer(trainer)

    suspend fun updateTrainer(trainer: TrainerEntity) = trainerDao.updateTrainer(trainer)

    suspend fun deleteTrainer(trainer: TrainerEntity) = trainerDao.deleteTrainer(trainer)

    suspend fun getTrainerByEmail(email: String): TrainerEntity? = trainerDao.getTrainerByEmail(email)
} 