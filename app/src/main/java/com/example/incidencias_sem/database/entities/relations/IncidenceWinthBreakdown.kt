package com.example.incidencias_sem.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.incidencias_sem.database.entities.Breakdown
import com.example.incidencias_sem.database.entities.Device
import com.example.incidencias_sem.database.entities.Device_type
import com.example.incidencias_sem.database.entities.Incidence

data class IncidenceWinthBreakdown(
    @Embedded val incidence : Incidence,
    @Relation(
        parentColumn = "breakdown_id",
        entityColumn = "id_breakdown"
    )
    val breakdown: List<Breakdown>
)
