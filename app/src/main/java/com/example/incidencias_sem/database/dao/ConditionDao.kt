package com.example.incidencias_sem.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.incidencias_sem.database.entities.ConditionIncidence
import com.example.incidencias_sem.database.entities.Incidence


@Dao
interface ConditionDao {

    @Query("SELECT * FROM estado_Inc")
    suspend fun findAll(): List<ConditionIncidence>

    @Query("SELECT * from estado_Inc where name=:name")
    suspend fun findOneByCondition(name: String): ConditionIncidence

    @Insert
    suspend fun save(conditionIncidence: ConditionIncidence)

    @Insert
    fun saveAll(lista: List<ConditionIncidence>)

    @Update
    fun update(lista: List<ConditionIncidence>)


}