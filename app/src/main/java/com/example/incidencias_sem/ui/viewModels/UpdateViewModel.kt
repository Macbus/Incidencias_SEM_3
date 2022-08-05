package com.example.incidencias_sem.ui.viewModels


import android.widget.Spinner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Update
import com.example.incidencias_sem.aplication.App
import com.example.incidencias_sem.database.entities.ConditionIncidence
import com.example.incidencias_sem.database.entities.Incidence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class UpdateViewModel : ViewModel() {

    val db = App.obtenerDB()

    fun getIncidence():MutableLiveData<List<Incidence>> {
        val _incidences = MutableLiveData<List<Incidence>>()
        viewModelScope.launch {
            val incidences = withContext(Dispatchers.IO) {
                db.incidenceDao().findAll()
            }
            _incidences.postValue(incidences)
        }
        return _incidences
    }

    fun getCondition() :MutableLiveData<List<ConditionIncidence>> {
        val _conditions = MutableLiveData<List<ConditionIncidence>>()
        viewModelScope.launch {
            val conditions = withContext(Dispatchers.IO) {
                db.conditionDao().findAll()
            }
            _conditions.postValue(conditions)
        }
        return _conditions
    }

    fun updateConditions(incidencia: String, estado: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val incidence = db.incidenceDao().getById(incidencia)
                incidence.condition_id = estado
                incidence.dateUpdated = Date()
                db.incidenceDao().updateCondition(incidence)
            }

        }

    }

    fun indEstado(id: String): MutableLiveData<Int> {
        val data = MutableLiveData<Int>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val incidence = db.incidenceDao().getById(id)
                data.postValue(incidence.condition_id.toInt())
            }
        }
        return data
    }


}