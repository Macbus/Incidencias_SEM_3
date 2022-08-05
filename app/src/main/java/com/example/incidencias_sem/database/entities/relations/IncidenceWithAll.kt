package com.example.incidencias_sem.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.incidencias_sem.database.entities.Breakdown
import com.example.incidencias_sem.database.entities.ConditionIncidence
import com.example.incidencias_sem.database.entities.Device_type
import com.example.incidencias_sem.database.entities.Incidence

data class IncidenceWithAll(
    @Embedded val incidence: Incidence,
    @Relation(parentColumn = "equipo", entityColumn = "id_type")
    val equipo: Device_type,
    @Relation(parentColumn = "averia", entityColumn = "id")
    val breakdown: Breakdown,
    @Relation(parentColumn = "estado", entityColumn = "id_condition")
    val condition: ConditionIncidence
)
