package com.example.trenerskaya_23.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.trenerskaya_23.R

object ThemeManager {
    private const val PREF_NAME = "theme_prefs"
    private const val KEY_THEME = "current_theme"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun setTheme(activity: Activity, isBlueTheme: Boolean) {
        // Сохраняем выбор темы
        getPrefs(activity).edit().putBoolean(KEY_THEME, isBlueTheme).apply()
        
        // Применяем тему
        activity.setTheme(if (isBlueTheme) R.style.Theme_Trenerskaya_Blue else R.style.Theme_Trenerskaya_Purple)
        
        // Пересоздаем активность для применения темы
        activity.recreate()
    }

    fun applyTheme(activity: Activity) {
        val isBlueTheme = getPrefs(activity).getBoolean(KEY_THEME, false)
        activity.setTheme(if (isBlueTheme) R.style.Theme_Trenerskaya_Blue else R.style.Theme_Trenerskaya_Purple)
    }

    fun isBlueTheme(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_THEME, false)
    }
} 