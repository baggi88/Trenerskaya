package com.example.trenerskaya_23.data.repository

import com.example.trenerskaya_23.data.dao.ExerciseDao
import com.example.trenerskaya_23.data.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) {
    fun getExercisesByTrainingId(trainingId: Long): Flow<List<ExerciseEntity>> =
        exerciseDao.getExercisesByTrainingId(trainingId)

    suspend fun getExerciseById(id: Long): ExerciseEntity? = exerciseDao.getExerciseById(id)

    suspend fun insertExercise(exercise: ExerciseEntity): Long = exerciseDao.insertExercise(exercise)

    suspend fun updateExercise(exercise: ExerciseEntity) = exerciseDao.updateExercise(exercise)

    suspend fun deleteExercise(exercise: ExerciseEntity) = exerciseDao.deleteExercise(exercise)

    suspend fun deleteExercisesByTrainingId(trainingId: Long) = exerciseDao.deleteExercisesByTrainingId(trainingId)
} 