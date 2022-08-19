package com.julianparrilla.cache.db

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.julianparrilla.cache.model.CachedResponseAllDragons

@Dao
interface DragonsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAll(cachedResponseAllCharacters: List<CachedResponseAllDragons>)

    @Query("SELECT * FROM dragonBooking")
    suspend fun getCachedData(): List<CachedResponseAllDragons>?

    @Query("DELETE FROM dragonBooking")
    suspend fun deleteAll()

    @RawQuery
    suspend fun getFilteredData(query: SupportSQLiteQuery):  List<CachedResponseAllDragons>?

    @Query("SELECT DISTINCT inbound_origin FROM dragonBooking")
    suspend fun getOrigins(): List<String>

    @Query("SELECT DISTINCT outbound_origin FROM dragonBooking")
    suspend fun getDestinations(): List<String>

}
