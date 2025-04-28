package com.example.trenerskaya_23.data

import android.content.Context
import com.example.trenerskaya_23.data.dao.ClientDao
import com.example.trenerskaya_23.data.dao.ExerciseDao
import com.example.trenerskaya_23.data.dao.TrainingDao
import com.example.trenerskaya_23.data.dao.TrainerDao
import com.example.trenerskaya_23.data.entity.ClientEntity
import com.example.trenerskaya_23.data.entity.ExerciseEntity
import com.example.trenerskaya_23.data.entity.TrainerEntity
import com.example.trenerskaya_23.data.entity.TrainingEntity
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseInitializer @Inject constructor(
    private val context: Context,
    private val trainerDao: TrainerDao,
    private val clientDao: ClientDao,
    private val trainingDao: TrainingDao,
    private val exerciseDao: ExerciseDao
) {
    suspend fun initializeDatabase() {
        try {
            // Создаем тестового тренера
            val trainer = TrainerEntity(
                id = 1L,
                name = "Тестовый тренер",
                email = "test@example.com",
                phone = "+79001234567",
                photoUrl = null,
                notes = null
            )
            trainerDao.insertTrainer(trainer)

            // Создаем тестового клиента
            val client = ClientEntity(
                id = 1L,
                name = "Тестовый клиент",
                email = "client@example.com",
                phone = "+79009876543",
                trainerId = 1L,
                photoUrl = null,
                height = 180.0,
                weight = 80.0,
                notes = "Тестовые заметки",
                goals = listOf("Похудение", "Набор мышечной массы")
            )
            clientDao.insertClient(client)

            // Создаем тестовую тренировку
            val training = TrainingEntity(
                id = 1L,
                name = "Тестовая тренировка",
                date = LocalDate.now(),
                duration = 60,
                trainerId = 1L,
                clientId = 1L,
                notes = "Тестовые заметки к тренировке"
            )
            trainingDao.insertTraining(training)

            // Создаем тестовое упражнение
            val exercise = ExerciseEntity(
                id = 1L,
                name = "Тестовое упражнение",
                sets = 3,
                reps = 12,
                weight = 50.0f,
                notes = "Тестовые заметки к упражнению",
                trainingId = 1L
            )
            exerciseDao.insertExercise(exercise)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }
} 