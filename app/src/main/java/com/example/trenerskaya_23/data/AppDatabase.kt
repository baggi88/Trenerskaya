package com.example.trenerskaya_23.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.trenerskaya_23.data.converter.Converters
import com.example.trenerskaya_23.data.dao.ClientDao
import com.example.trenerskaya_23.data.dao.ExerciseDao
import com.example.trenerskaya_23.data.dao.TrainerDao
import com.example.trenerskaya_23.data.dao.TrainingDao
import com.example.trenerskaya_23.data.entity.ClientEntity
import com.example.trenerskaya_23.data.entity.ExerciseEntity
import com.example.trenerskaya_23.data.entity.TrainerEntity
import com.example.trenerskaya_23.data.entity.TrainingEntity

@Database(
    entities = [
        TrainerEntity::class,
        ClientEntity::class,
        TrainingEntity::class,
        ExerciseEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trainerDao(): TrainerDao
    abstract fun clientDao(): ClientDao
    abstract fun trainingDao(): TrainingDao
    abstract fun exerciseDao(): ExerciseDao
} 