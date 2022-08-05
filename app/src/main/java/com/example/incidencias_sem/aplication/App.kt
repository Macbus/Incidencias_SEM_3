package com.example.incidencias_sem.aplication

import android.app.Application
import android.content.Context
import androidx.core.content.edit
import com.example.incidencias_sem.database.AppDatabase

class App : Application() {

    init {
        app = this
    }

    companion object {
        private lateinit var app: App

        private var db: AppDatabase? = null
        fun obtenerDB(): AppDatabase {
            return db!!
        }

        fun saveUser(id: Long, username: String, logued: Boolean) {
            val prefs = app.getSharedPreferences(Constants.prefsName, Context.MODE_PRIVATE)
            prefs.edit {
                putLong(Constants.prefsidUser, id)
                putString(Constants.prefsUsername, username)
                putBoolean(Constants.prefsIsLogued, logued)
                apply()
            }
        }

        fun getUserID(): Long {
            val prefs = app.getSharedPreferences(Constants.prefsName, Context.MODE_PRIVATE)
            return prefs.getLong(Constants.prefsidUser, 0)
        }

        fun getUsername(): String {
            val prefs = app.getSharedPreferences(Constants.prefsName, Context.MODE_PRIVATE)
            return prefs.getString(Constants.prefsUsername, "")!!
        }

        fun isLogued(): Boolean {
            val prefs = app.getSharedPreferences(Constants.prefsName, Context.MODE_PRIVATE)
            return prefs.getBoolean(Constants.prefsIsLogued, false)
        }

        fun getDeviceTypeid(): Long {
            val prefs =
                app.getSharedPreferences(Constants.prefsidDevice_type, Context.MODE_PRIVATE)
            return prefs.getLong(Constants.prefsidDevice_type, 0)

        }
        fun getDevice(): String {
            val prefs =
                app.getSharedPreferences(Constants.prefsdevice, Context.MODE_PRIVATE)
            return prefs.getString(Constants.prefsdevice, "")!!
        }

        fun getIssi(): String {
            val prefs =
                app.getSharedPreferences(Constants.prefsissi, Context.MODE_PRIVATE)
            return prefs.getString(Constants.prefsissi, "")!!
        }
        fun getIncidence(): String {
            val prefs =
                app.getSharedPreferences(Constants.prefsincidence, Context.MODE_PRIVATE)
            return prefs.getString(Constants.prefsincidence, "")!!
        }
        fun getContractor(): Long {
            val prefs =
                app.getSharedPreferences(Constants.prefscontractor, Context.MODE_PRIVATE)
            return prefs.getLong(Constants.prefscontractor, 0)!!
        }

        fun closeSession() {
            val prefs = app.getSharedPreferences(Constants.prefsName, Context.MODE_PRIVATE)
            prefs.edit {
                clear()
                apply()
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDB(applicationContext)
    }
}