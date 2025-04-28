package com.example.trenerskaya_23.ui.calculator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.pow
import kotlin.math.roundToInt

class CalculatorViewModel : ViewModel() {

    private val _bmiResult = MutableLiveData<Double>()
    val bmiResult: LiveData<Double> = _bmiResult

    private val _bmiCategory = MutableLiveData<String>()
    val bmiCategory: LiveData<String> = _bmiCategory

    private val _calories = MutableLiveData<Int>()
    val calories: LiveData<Int> = _calories

    private val _protein = MutableLiveData<Int>()
    val protein: LiveData<Int> = _protein

    private val _fats = MutableLiveData<Int>()
    val fats: LiveData<Int> = _fats

    private val _carbs = MutableLiveData<Int>()
    val carbs: LiveData<Int> = _carbs

    fun calculateBMI(weight: Double, height: Double) {
        val heightInMeters = height / 100
        val bmi = weight / heightInMeters.pow(2)
        _bmiResult.value = (bmi * 10).roundToInt() / 10.0

        _bmiCategory.value = when {
            bmi < 16 -> "Выраженный дефицит массы тела"
            bmi < 18.5 -> "Недостаточная масса тела"
            bmi < 25 -> "Нормальная масса тела"
            bmi < 30 -> "Избыточная масса тела"
            bmi < 35 -> "Ожирение I степени"
            bmi < 40 -> "Ожирение II степени"
            else -> "Ожирение III степени"
        }
    }

    fun calculateMacros(
        weight: Double,
        height: Double,
        age: Int,
        isMale: Boolean,
        activityLevel: ActivityLevel
    ) {
        // Формула Миффлина-Сан Жеора
        val bmr = if (isMale) {
            (10 * weight) + (6.25 * height) - (5 * age) + 5
        } else {
            (10 * weight) + (6.25 * height) - (5 * age) - 161
        }

        val tdee = (bmr * activityLevel.multiplier).roundToInt()
        _calories.value = tdee

        // Расчет БЖУ по рекомендациям ВОЗ
        _protein.value = ((weight * 2.0) * 10).roundToInt()
        _fats.value = (tdee * 0.25 / 9).roundToInt()
        // _carbs.value = ((tdee - (_protein.value!! * 4) - (_fats.value!! * 9)) / 4).roundToInt() // Закомментируем зависимую строку
    }

    enum class ActivityLevel(val title: String, val multiplier: Double) {
        SEDENTARY("Сидячий образ жизни", 1.2),
        LIGHTLY_ACTIVE("Легкая активность", 1.375),
        MODERATELY_ACTIVE("Умеренная активность", 1.55),
        VERY_ACTIVE("Высокая активность", 1.725),
        EXTRA_ACTIVE("Очень высокая активность", 1.9)
    }
} 