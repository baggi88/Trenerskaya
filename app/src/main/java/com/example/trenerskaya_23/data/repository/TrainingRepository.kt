package com.example.trenerskaya_23.data.repository

import com.example.trenerskaya_23.data.dao.TrainingDao
import com.example.trenerskaya_23.data.entity.TrainingEntity
import com.example.trenerskaya_23.data.entity.ExerciseEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingRepository @Inject constructor(
    private val trainingDao: TrainingDao
) {
    suspend fun getAllTrainings(): List<TrainingEntity> {
        return trainingDao.getAllTrainings()
    }

    suspend fun getTrainingById(id: Long): TrainingEntity? {
        return trainingDao.getTrainingById(id)
    }

    suspend fun insertTraining(training: TrainingEntity): Long {
        return trainingDao.insertTraining(training)
    }

    suspend fun updateTraining(training: TrainingEntity) {
        trainingDao.updateTraining(training)
    }

    suspend fun deleteTraining(training: TrainingEntity) {
        trainingDao.deleteTraining(training)
    }

    suspend fun deleteAllTrainings() {
        trainingDao.deleteAllTrainings()
    }

    suspend fun getExercisesForTraining(trainingId: Long): List<ExerciseEntity> {
        return trainingDao.getExercisesForTraining(trainingId)
    }

    suspend fun insertExercise(exercise: ExerciseEntity) {
        trainingDao.insertExercise(exercise)
    }

    suspend fun deleteExercise(exercise: ExerciseEntity) {
        trainingDao.deleteExercise(exercise)
    }
} 