package com.example.incidencias_sem.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contractor")
data class Contractor(
    val name: String,
) {
    @PrimaryKey(autoGenerate = true)
    var contractorid: Long = 0

    override fun toString(): String {
        return "Contractor(name='$name', id=$contractorid)"
    }


}
