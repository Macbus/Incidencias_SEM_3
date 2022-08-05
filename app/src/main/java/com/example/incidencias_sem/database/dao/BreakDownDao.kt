package com.example.incidencias_sem.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.incidencias_sem.database.entities.Breakdown


@Dao
interface BreakDownDao {

    @Query("SELECT * FROM breakdown")
    suspend fun findAll():List<Breakdown>
    @Query("SELECT * FROM breakdown where name=:name")
    suspend fun findByName(name: String): Breakdown?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBreakdown(breakdown:Breakdown)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(lista:List<Breakdown>)

    }

