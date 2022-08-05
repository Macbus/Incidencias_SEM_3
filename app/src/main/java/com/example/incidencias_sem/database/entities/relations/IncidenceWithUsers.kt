package com.example.incidencias_sem.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.incidencias_sem.database.entities.Device
import com.example.incidencias_sem.database.entities.Incidence
import com.example.incidencias_sem.database.entities.Users

data class IncidenceWithUsers(
    @Embedded val users: Users,
    @Relation(
        parentColumn = "id",
        entityColumn = "usuario"
    )
    val incidence: List<Incidence>
)
