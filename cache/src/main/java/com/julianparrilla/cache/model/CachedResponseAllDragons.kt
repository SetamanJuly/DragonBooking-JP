package com.julianparrilla.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "responses")
data class CachedResponseAllDragons(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "responseDragons")
    var response: String
)
