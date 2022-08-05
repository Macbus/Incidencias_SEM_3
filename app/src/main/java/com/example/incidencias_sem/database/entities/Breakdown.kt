package com.example.incidencias_sem.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.incidencias_sem.aplication.App

@Entity(tableName = "breakdown")
data class Breakdown(
    val name: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    override fun toString(): String {
        return "Breakdown(id=$id, name='$name')"
    }
}