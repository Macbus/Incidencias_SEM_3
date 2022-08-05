package com.example.incidencias_sem.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.incidencias_sem.database.entities.Contractor

@Dao
interface ContractorDao {

    @Query("SELECT * FROM contractor")
    suspend fun findAll():List<Contractor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContractor(contractor: Contractor)

    @Insert
    fun saveAll(lista:List<Contractor>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(lista:List<Contractor>)
}