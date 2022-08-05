package com.example.incidencias_sem.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.incidencias_sem.database.entities.Device
import com.example.incidencias_sem.database.entities.ISSI

data class IssiWithDevice(
    @Embedded val device : Device,
    @Relation(
        parentColumn = "id_TEI",
        entityColumn = "TEI_id"
    )
    val issi: List<ISSI>
)
