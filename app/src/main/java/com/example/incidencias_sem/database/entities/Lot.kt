package com.example.incidencias_sem.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lot")
data class Lot(
    val lote : String,
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    override fun toString(): String {
        return "Lot( id=$id,name='$lote')"
    }


}
