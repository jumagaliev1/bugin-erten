package com.example.bugin_erten.repository

import androidx.lifecycle.LiveData
import com.example.bugin_erten.database.QaraSoz
import com.example.bugin_erten.database.QaraSozDao
import com.example.bugin_erten.network.Api
import com.example.bugin_erten.network.QaraSozProperty

class QaraSozRepository(private val dao: QaraSozDao) {
    suspend fun add(qaraSoz: QaraSoz) {
        dao.insert(qaraSoz)
    }

    suspend fun update(qaraSoz: QaraSoz) {
        dao.update(qaraSoz)
    }

    suspend fun getById(key: Long): QaraSoz? {
        return dao.get(key)
    }

    fun clear() {
        dao.clear()
    }

    suspend fun deleteById(key: Long) {
        dao.delete(key)
    }

    fun getAll(): LiveData<List<QaraSoz?>> {
        return dao.getAll()
    }
    suspend fun getFromNetwork(): QaraSozProperty {
        return Api.retrofitService.getProperties()
    }
}