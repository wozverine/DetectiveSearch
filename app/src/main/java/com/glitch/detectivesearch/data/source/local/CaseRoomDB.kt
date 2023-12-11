package com.glitch.detectivesearch.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.glitch.detectivesearch.data.model.response.CaseEntity

@Database(entities = [CaseEntity::class], version = 1)
abstract class CaseRoomDB : RoomDatabase() {
	abstract fun productDao(): CaseDao
}