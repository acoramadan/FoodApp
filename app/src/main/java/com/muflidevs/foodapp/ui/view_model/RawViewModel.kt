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
import com.muflidevs.foodapp.R
import com.muflidevs.foodapp.data.remote.entity.Sayuran

class RawViewModel: ViewModel() {
    private val _dataJson = MutableStateFlow<String?>(null)
    val jsonData: StateFlow<String?> get() = _dataJson

    private val _sayuranList = MutableStateFlow<List<Sayuran>>(emptyList())
    val sayuranList: StateFlow<List<Sayuran>> get() = _sayuranList

    fun loadLaporanFromJson(context: Context, resourcesId: Int) {
        viewModelScope.launch {
            val jsonString = withContext (Dispatchers.IO) {
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
    private fun parseJsonData(jsonString: String) {
        val gson = Gson()
        val laporan = gson.fromJson(jsonString, Laporan::class.java)
        _sayuranList.value = laporan.laporan
    }
}