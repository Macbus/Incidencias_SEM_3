package com.example.incidencias_sem.database.entities


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.incidencias_sem.aplication.App
import com.example.incidencias_sem.aplication.App.Companion.getContractor


@Entity(tableName = "device")
data class Device(
    @PrimaryKey(autoGenerate = false)
    val id_TEi: String,
    val serial_number: String,
    val license: String,
    @ColumnInfo(name = "type_id")
    var type_id:Long = App.getDeviceTypeid(),
    @ColumnInfo(name = "contractor_id")
    var contractorid:Long = getContractor()


) {

    override fun toString(): String {
        return "Device(id_TEi='$id_TEi', serial_number='$serial_number', license='$license', type_id='$type_id',constructor_id='$contractorid')"
    }
}