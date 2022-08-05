package com.example.incidencias_sem.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.incidencias_sem.aplication.App
import java.util.*

@Entity(tableName = "estado_Inc")
data class ConditionIncidence(
    val name:String
) {
    @PrimaryKey(autoGenerate = true)
    var id_condition:Long =0

    var dateUpdated: Date = Date(System.currentTimeMillis())

    override fun toString(): String {
        return "ConditionIncidence(id_condition='$id_condition', name='$name',update='$dateUpdated)"
    }
}