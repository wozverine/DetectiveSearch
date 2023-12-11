package com.glitch.detectivesearch.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.glitch.detectivesearch.data.model.response.CaseEntity

@Database(entities = [CaseEntity::class], version = 1, exportSchema = false)
abstract class CaseRoomDB : RoomDatabase() {
	abstract fun caseDao(): CaseDao

	companion object {
		@Volatile
		private var INSTANCE: CaseRoomDB? = null

		fun getInstance(context: Context): CaseRoomDB {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					CaseRoomDB::class.java,
					"case_database"
				).build()
				INSTANCE = instance
				instance
			}
		}
	}
}