package com.example.incidencias_sem.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.incidencias_sem.aplication.App


@Entity(tableName = "device_type")
data class Device_type(
    val name: String
) {
    @PrimaryKey(autoGenerate = true)
    var id_type: Long = 0

    override fun toString(): String {
        return "Device_type(id_type='$id_type', name='$name')"
    }
}