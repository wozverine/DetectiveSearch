package com.glitch.detectivesearch.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.glitch.detectivesearch.data.model.response.EvalEntity

@Database(entities = [EvalEntity::class], version = 1, exportSchema = false)
abstract class EvalRoomDB : RoomDatabase() {
	abstract fun evalDao(): EvalDao

	companion object {
		@Volatile
		private var INSTANCE: EvalRoomDB? = null

		fun getInstance(context: Context): EvalRoomDB {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext, EvalRoomDB::class.java, "eval_database"
				).build()
				INSTANCE = instance
				instance
			}
		}
	}
}