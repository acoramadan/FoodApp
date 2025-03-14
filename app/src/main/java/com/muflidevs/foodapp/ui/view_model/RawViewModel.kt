package com.muflidevs.foodapp.ui.view_model

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.muflidevs.foodapp.data.remote.entity.Laporan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.InputStream
import android.util.Log
import com.muflidevs.foodapp.data.remote.entity.InputanResponse
import com.muflidevs.foodapp.data.remote.entity.Sayuran

class RawViewModel : ViewModel() {
    private val _dataJson = MutableStateFlow<String?>(null)
    val jsonData: StateFlow<String?> get() = _dataJson

    private val _sayuranList = MutableStateFlow<List<Sayuran>>(emptyList())
    val sayuranList: StateFlow<List<Sayuran>> get() = _sayuranList

    val result = MutableStateFlow<Sayuran?>(null)

    private val _inputanResult = MutableStateFlow<InputanResponse?>(null)
    val inputanResult: StateFlow<InputanResponse?> get() = _inputanResult

    fun loadLaporanFromJson(context: Context, resourcesId: Int) {
        viewModelScope.launch {
            val jsonString = withContext(Dispatchers.IO) {
                val inputStream: InputStream = context.resources.openRawResource(resourcesId)
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charsets.UTF_8)
            }
            _dataJson.value = jsonString
            parseJsonData(jsonString)
        }
    }

    fun loadLaporanFromJsonInputan(context: Context, resourcesId: Int) {
        viewModelScope.launch {
            val jsonString = withContext(Dispatchers.IO) {
                val inputStream: InputStream = context.resources.openRawResource(resourcesId)
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charsets.UTF_8)
            }
            _dataJson.value = jsonString
           parseJsonDataInputan(jsonString)
        }
    }

    fun loadLaporanFromJsonById(id: Int): StateFlow<Sayuran?> {
        viewModelScope.launch {
            Log.d("LoadLaporan$", "${_sayuranList.value}")
            Log.d("LoadLaporan$", "id : $id")
            _sayuranList.collect { sayuranList ->
                val sayuran = sayuranList.find { it.id == id }
                Log.d("LoadLaporan", "Sayuran ditemukan: $sayuran")
                result.value = sayuran
            }
        }
        Log.d("LoadLaporan","$result")
        return result
    }

    private fun parseJsonData(jsonString: String) {
        val gson = Gson()
        val laporan = gson.fromJson(jsonString, Laporan::class.java)
        Log.d("LoadLaporan", "Sayuran ditemukan: ${laporan.laporan}")
        _sayuranList.value = laporan.laporan
    }
    private fun parseJsonDataInputan(jsonString: String) {
        val inputan = Gson().fromJson(jsonString, InputanResponse::class.java)
        Log.d("Load Laporan", "Data dummy : ${inputan}")
        _inputanResult.value = inputan
    }
}