package com.example.incidencias_sem.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.incidencias_sem.database.entities.ConditionIncidence
import com.example.incidencias_sem.database.entities.Incidence

data class IncidenceWithCondition(
    @Embedded val incidence : Incidence,
    @Relation(
        parentColumn = "condition_id",
        entityColumn = "id_condition"
    )
    val condition: List<ConditionIncidence>
)
