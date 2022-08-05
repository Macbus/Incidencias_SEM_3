package com.example.incidencias_sem.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.incidencias_sem.aplication.App
import java.util.*

@Entity(tableName = "incidence")
data class Incidence(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id_incidence: String, // PRIMARY KEY NO AUTO_INCREMENT
    @ColumnInfo(name = "issi")
    val Issi_id:String,
    @ColumnInfo(name = "indicativo")
    val call_sign: String,
    @ColumnInfo(name = "centro")
    val location: String,
    @ColumnInfo(name = "equipo")
    val type_id:String,
    @ColumnInfo(name = "averia")
    val breakdown_id:String,
    @ColumnInfo(name = "estado")
    var condition_id:String

){
    @ColumnInfo(name = "usuario")
    var users_id:Long = App.getUserID()
    @ColumnInfo(name = "fecha_created")
    var dateAdded: Date = Date(System.currentTimeMillis())
    @ColumnInfo(name = "fecha_update", defaultValue = "0")
    var dateUpdated: Date = Date(System.currentTimeMillis())
    override fun toString(): String {
        return "Incidence(id_incidence='$id_incidence', " +
                "Issi_id='$Issi_id', call_sign='$call_sign', location='$location', " +
                "type_id='$type_id', breakdown_id='$breakdown_id', condition_id='$condition_id', " +
                "users_id='$users_id', dateCreated=$dateAdded), dateUpdated=$dateUpdated"
    }

}

