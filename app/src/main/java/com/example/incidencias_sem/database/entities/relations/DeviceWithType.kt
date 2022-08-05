package com.example.incidencias_sem.database.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.incidencias_sem.database.entities.Device
import com.example.incidencias_sem.database.entities.Device_type


data class DeviceWithType(
    @Embedded val d_type : Device_type,
    @Relation(
        parentColumn = "id_type",
        entityColumn = "type_id"
    )
    val device: List<Device>
)
