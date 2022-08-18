package com.julianparrilla.cache.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.julianparrilla.cache.model.CachedResponseAllDragons

@Dao
interface DragonsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertOrIgnore(cachedResponseAllCharacters: CachedResponseAllDragons)

    @Query("SELECT * FROM responses")
    suspend fun getCachedData(): CachedResponseAllDragons?

}
