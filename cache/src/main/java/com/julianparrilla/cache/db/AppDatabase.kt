package com.julianparrilla.cache.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.julianparrilla.cache.model.CachedResponseAllDragons

@Database(entities = [CachedResponseAllDragons::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDragonsDao(): DragonsDao

    companion object {
        const val DATABASE_NAME = "app_dragons_app_jpe_db"
    }
}
