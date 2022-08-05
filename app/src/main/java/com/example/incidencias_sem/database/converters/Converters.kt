package com.example.incidencias_sem.database.converters

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun toDate(date:Long?): Date?{
        return date?.let{ millis->
            Date(millis)
        }
    }

    @TypeConverter
    fun fromDate(date:Date):Long{
        return date.time
    }
}