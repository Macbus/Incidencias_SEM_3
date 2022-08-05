package com.example.incidencias_sem.ui.viewModels


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incidencias_sem.aplication.App
import com.example.incidencias_sem.database.entities.Breakdown
import com.example.incidencias_sem.database.entities.ConditionIncidence
import com.example.incidencias_sem.database.entities.Device_type
import com.example.incidencias_sem.database.entities.Incidence
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CreateViewModel : ViewModel() {

    val db = App.obtenerDB()
    private val _devices: MutableLiveData<List<Device_type>> = MutableLiveData()
    private val _breakdown: MutableLiveData<List<Breakdown>> = MutableLiveData()
    private val _conditions: MutableLiveData<List<ConditionIncidence>> = MutableLiveData()

    fun getDevices() =_devices
    fun getbreakdowns() =_breakdown
    fun getConditions() =_conditions

    init {
        getDevice()
        getBreakdown()
        getCondition()
    }

    fun insertIncidence(incidence: Incidence) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.incidenceDao().insert(incidence)

            }
        }
    }
    private fun getDevice(){
        viewModelScope.launch {
            val devices = withContext(Dispatchers.IO){
                db.deviceTypeDao().findAll()
            }
            _devices.postValue(devices)
        }
    }
    private fun getBreakdown(){
        viewModelScope.launch {
            val breakdowns = withContext(Dispatchers.IO){
                db.breakDownDao().findAll()
            }
            _breakdown.postValue(breakdowns)
        }
    }
    private fun getCondition(){
        viewModelScope.launch {
            val conditions = withContext(Dispatchers.IO){
                db.conditionDao().findAll()
            }
            _conditions.postValue(conditions)
        }
    }
}

