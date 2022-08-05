package com.example.incidencias_sem.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.incidencias_sem.aplication.App

@Entity(tableName = "issi")
data class ISSI(
    @PrimaryKey(autoGenerate = false)
    val id_ISSI:String,

)

{
    override fun toString(): String {
        return "ISSI(id_ISSI='$id_ISSI')"
    }
}