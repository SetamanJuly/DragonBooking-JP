package com.julianparrilla.cache.source

import com.google.gson.Gson
import com.julianparrilla.cache.db.DragonsDao
import com.julianparrilla.cache.model.CachedResponseAllDragons
import com.julianparrilla.data.datasource.cache.DragonsCacheDataSource
import com.julianparrilla.data.entity.DragonsModel

class DragonsCacheDataSourceImpl(
    private val dragonsDao: DragonsDao
) : DragonsCacheDataSource {

    override suspend fun insertCharacters(resultsEntity: DragonsModel) {
        dragonsDao.insertOrIgnore(
            CachedResponseAllDragons(
                response = Gson().toJson(resultsEntity)
            )
        )
    }

    override suspend fun searchById(idCharacter: Int): DragonsModel? {
        val listCached = dragonsDao.getCachedData()?.let {
            Gson().fromJson(it.response, DragonsModel::class.java)
        }

        listCached?.let {
           // it.data.results.firstOrNull { it.id == idCharacter }?.apply {
           //     it.data.results.clear()
           //     it.data.results.add(this)
           // }
        }

        return listCached
    }

    override suspend fun getAllCharacters(): DragonsModel? {
        return dragonsDao.getCachedData()?.let {
            Gson().fromJson(it.response, DragonsModel::class.java)
        }
    }
}
