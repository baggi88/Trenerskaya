package com.example.trenerskaya_23.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object PrefsManager {
    private const val PREFS_NAME = "trenerskaya_prefs"
    private const val KEY_CURRENT_TRAINER_ID = "current_trainer_id"
    private const val KEY_NOTIFICATIONS_ENABLED = "notifications_enabled"
    private const val KEY_REMINDERS_ENABLED = "reminders_enabled"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getCurrentTrainerId(context: Context): String? {
        return getPrefs(context).getString(KEY_CURRENT_TRAINER_ID, null)
    }

    fun setCurrentTrainerId(context: Context, trainerId: String) {
        getPrefs(context).edit {
            putString(KEY_CURRENT_TRAINER_ID, trainerId)
        }
    }

    fun clearCurrentTrainerId(context: Context) {
        getPrefs(context).edit {
            remove(KEY_CURRENT_TRAINER_ID)
        }
    }

    fun getNotificationsEnabled(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_NOTIFICATIONS_ENABLED, true)
    }

    fun setNotificationsEnabled(context: Context, enabled: Boolean) {
        getPrefs(context).edit().putBoolean(KEY_NOTIFICATIONS_ENABLED, enabled).apply()
    }

    fun getRemindersEnabled(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_REMINDERS_ENABLED, true)
    }

    fun setRemindersEnabled(context: Context, enabled: Boolean) {
        getPrefs(context).edit().putBoolean(KEY_REMINDERS_ENABLED, enabled).apply()
    }
} 