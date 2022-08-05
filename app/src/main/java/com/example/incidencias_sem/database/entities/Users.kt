package com.example.incidencias_sem.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "users")
data class Users(val name: String,val surname: String,val email: String,val password: String) {
    @PrimaryKey(autoGenerate = true)
    var id:Long =0

    @ColumnInfo(name="create_at")
    var createAt: Date =Date(System.currentTimeMillis())
    @ColumnInfo(name="update_at")
    var updateAt: Date =Date(System.currentTimeMillis())


    override fun toString(): String {
        return "Users(name='$name', surname='$surname', email='$email', password='$password')"
    }
}