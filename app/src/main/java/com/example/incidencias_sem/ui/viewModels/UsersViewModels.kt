package com.example.incidencias_sem.ui.viewModels


import androidx.lifecycle.*
import com.example.incidencias_sem.aplication.App
import com.example.incidencias_sem.database.entities.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModels : ViewModel() {

    val db = App.obtenerDB()

    fun save(user: Users) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.usersDao().save(user)
            }
        }
    }

    fun login(email: String): LiveData<Users?> {
        val liveData = MutableLiveData<Users?>()
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                db.usersDao().findOneByEmail(email)
            }
               liveData.postValue(user)
        }
        return liveData
    }
}