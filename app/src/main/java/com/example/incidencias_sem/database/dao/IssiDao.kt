package com.example.incidencias_sem.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.incidencias_sem.database.entities.ISSI


@Dao
interface IssiDao {

    @Query("SELECT * FROM issi")
    suspend fun findAll(): List<ISSI>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(lista: List<ISSI>)
}


