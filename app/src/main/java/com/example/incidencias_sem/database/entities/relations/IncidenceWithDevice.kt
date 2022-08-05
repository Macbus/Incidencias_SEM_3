package com.example.incidencias_sem.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.incidencias_sem.database.entities.Device
import com.example.incidencias_sem.database.entities.Incidence

data class IncidenceWithDevice(
    @Embedded val device: Device,
    @Relation(
        parentColumn = "id_TEI",
        entityColumn = "issi"
    )
    val incidence: List<Incidence>
)
