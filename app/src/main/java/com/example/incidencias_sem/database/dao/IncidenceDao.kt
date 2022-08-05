package com.example.incidencias_sem.database.dao

import androidx.room.*
import com.example.incidencias_sem.database.entities.Incidence
import com.example.incidencias_sem.database.entities.relations.IncidenceWithAll
import kotlinx.coroutines.flow.Flow


@Dao
interface IncidenceDao {

    @Query("SELECT * FROM incidence WHERE id =:id")
    suspend fun getById(id: String): Incidence

    @Transaction
    @Query("SELECT * from INCIDENCE where id=:id")
    suspend fun getAllById(id:String):IncidenceWithAll

    @Query("SELECT * from incidence where estado != '4' ORDER BY id ASC  ")
    fun getItems(): Flow<List<Incidence>>

    @Query("SELECT * FROM incidence where estado != '4'")
    suspend fun findAll(): List<Incidence>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(incidence: Incidence)

    @Update
    fun updateCondition(incidence: Incidence)

    @Delete
    suspend fun delete(incidence: Incidence)


}