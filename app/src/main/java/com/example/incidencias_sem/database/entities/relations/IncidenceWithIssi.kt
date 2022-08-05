package com.example.incidencias_sem.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.incidencias_sem.database.entities.ISSI
import com.example.incidencias_sem.database.entities.Incidence

data class IncidenceWithIssi(
    @Embedded val issi: ISSI,
    @Relation(
        parentColumn = "id_ISSI",// id del IssiDao
        entityColumn = "Issi_id"// id de la incidencia
    )
    val incidence: List<Incidence>
)
