package com.example.incidencias_sem.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.incidencias_sem.database.entities.Lot


@Dao
interface LotDao {

    @Query("SELECT * FROM lot")
    suspend fun findAll():List<Lot>

    @Query("SELECT * FROM lot WHERE lote=:name")
    fun findByDevice(name: String): List<Lot>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLot(lot: Lot)

    @Insert
    fun saveall(lista:List<Lot>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(lista:List<Lot>)
}