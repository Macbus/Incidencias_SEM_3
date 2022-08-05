package com.example.incidencias_sem.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.incidencias_sem.database.entities.Device_type
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceTypeDao {


    @Query("SELECT * FROM device_type WHERE name=:device_type")
    fun findByDevice(device_type: String): List<Device_type>

    @Query("SELECT * FROM device_type")
    fun findAll(): List<Device_type>

    @Insert
    fun saveAll(lista: List<Device_type>)

}