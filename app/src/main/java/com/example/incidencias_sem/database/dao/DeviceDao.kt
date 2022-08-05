package com.example.incidencias_sem.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.incidencias_sem.database.entities.Device

@Dao
interface DeviceDao {

    @Query("SELECT * FROM device")
    suspend fun findAll():List<Device>
    @Insert
    fun saveAll(lista: List<Device>)
}