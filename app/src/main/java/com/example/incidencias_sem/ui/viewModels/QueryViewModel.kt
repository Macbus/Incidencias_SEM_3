package com.example.incidencias_sem.ui.viewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incidencias_sem.aplication.App
import com.example.incidencias_sem.database.entities.ConditionIncidence
import com.example.incidencias_sem.database.entities.Incidence
import com.example.incidencias_sem.database.entities.relations.IncidenceWithAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QueryViewModel : ViewModel() {

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
    fun indEstado(id: String): MutableLiveData<IncidenceWithAll> {
        val data = MutableLiveData<IncidenceWithAll>()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val incidence = db.incidenceDao().getAllById(id)
                data.postValue(incidence)
            }
        }
        return data
    }

}