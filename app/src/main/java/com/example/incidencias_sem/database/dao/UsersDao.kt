package com.example.incidencias_sem.database.dao

import androidx.room.*
import com.example.incidencias_sem.database.entities.Users

@Dao
interface UsersDao {
    @Query("SELECT * from users")
    suspend fun findAll():List<Users>

    @Query("SELECT * from users where email=:emailUsuario")
    suspend fun findOneByEmail(emailUsuario: String): Users?

    @Query("SELECT * from users where id = :id")
    suspend fun findOneById(id: Long):Users

    @Insert
    suspend fun  save(users: Users):Long

    @Update
    suspend fun update(usuario: Users)

    @Delete
    suspend fun delete(usuario: Users)
}