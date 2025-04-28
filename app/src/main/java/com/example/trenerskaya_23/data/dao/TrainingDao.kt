package com.example.trenerskaya_23.data.dao

import androidx.room.*
import com.example.trenerskaya_23.data.entity.TrainingEntity
import com.example.trenerskaya_23.data.entity.ExerciseEntity
import com.example.trenerskaya_23.data.model.TrainingWithExercises
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface TrainingDao {
    @Query("SELECT * FROM trainings ORDER BY date DESC")
    suspend fun getAllTrainings(): List<TrainingEntity>

    @Query("SELECT * FROM trainings WHERE id = :id")
    suspend fun getTrainingById(id: Long): TrainingEntity?

    @Insert
    suspend fun insertTraining(training: TrainingEntity): Long

    @Update
    suspend fun updateTraining(training: TrainingEntity)

    @Delete
    suspend fun deleteTraining(training: TrainingEntity)

    @Query("DELETE FROM trainings")
    suspend fun deleteAllTrainings()

    @Query("SELECT * FROM exercises WHERE trainingId = :trainingId")
    suspend fun getExercisesForTraining(trainingId: Long): List<ExerciseEntity>

    @Insert
    suspend fun insertExercise(exercise: ExerciseEntity)

    @Delete
    suspend fun deleteExercise(exercise: ExerciseEntity)

    @Query("SELECT * FROM trainings WHERE trainerId = :trainerId")
    fun getTrainingsByTrainerId(trainerId: Long): Flow<List<TrainingEntity>>

    @Query("SELECT * FROM trainings WHERE trainerId = :trainerId AND date BETWEEN :startDate AND :endDate")
    fun getTrainingsByDateRange(trainerId: Long, startDate: LocalDate, endDate: LocalDate): Flow<List<TrainingEntity>>

    @Query("SELECT * FROM trainings WHERE trainerId = :trainerId AND date = :date")
    fun getTrainingsByDate(trainerId: Long, date: LocalDate): Flow<List<TrainingEntity>>

    @Query("SELECT * FROM trainings WHERE trainerId = :trainerId AND date = :date")
    suspend fun getTrainingsByTrainerIdAndDate(trainerId: Long, date: LocalDate): List<TrainingEntity>

    @Query("SELECT * FROM trainings WHERE clientId = :clientId")
    suspend fun getTrainingsByClientId(clientId: Long): List<TrainingEntity>

    @Transaction
    @Query("SELECT * FROM trainings WHERE id = :id")
    suspend fun getTrainingWithExercisesById(id: Long): TrainingWithExercises?

    @Transaction
    @Query("SELECT * FROM trainings")
    fun getAllTrainingsWithExercises(): Flow<List<TrainingWithExercises>>

    @Query("SELECT * FROM trainings")
    fun getAllTrainingsFlow(): Flow<List<TrainingEntity>>
} 