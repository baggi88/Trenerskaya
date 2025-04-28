package com.example.trenerskaya_23.di

import android.content.Context
import androidx.room.Room
import com.example.trenerskaya_23.data.AppDatabase
import com.example.trenerskaya_23.data.dao.ClientDao
import com.example.trenerskaya_23.data.dao.ExerciseDao
import com.example.trenerskaya_23.data.dao.TrainerDao
import com.example.trenerskaya_23.data.dao.TrainingDao
import com.example.trenerskaya_23.data.repository.ClientRepository
import com.example.trenerskaya_23.data.repository.ExerciseRepository
import com.example.trenerskaya_23.data.repository.TrainerRepository
import com.example.trenerskaya_23.data.repository.TrainingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTrainerDao(database: AppDatabase): TrainerDao {
        return database.trainerDao()
    }

    @Provides
    @Singleton
    fun provideClientDao(database: AppDatabase): ClientDao {
        return database.clientDao()
    }

    @Provides
    @Singleton
    fun provideTrainingDao(database: AppDatabase): TrainingDao {
        return database.trainingDao()
    }

    @Provides
    @Singleton
    fun provideExerciseDao(database: AppDatabase): ExerciseDao {
        return database.exerciseDao()
    }

    @Provides
    @Singleton
    fun provideTrainerRepository(trainerDao: TrainerDao): TrainerRepository {
        return TrainerRepository(trainerDao)
    }

    @Provides
    @Singleton
    fun provideClientRepository(clientDao: ClientDao): ClientRepository {
        return ClientRepository(clientDao)
    }

    @Provides
    @Singleton
    fun provideTrainingRepository(
        trainingDao: TrainingDao
    ): TrainingRepository {
        return TrainingRepository(trainingDao)
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(exerciseDao: ExerciseDao): ExerciseRepository {
        return ExerciseRepository(exerciseDao)
    }
} 