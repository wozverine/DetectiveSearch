package com.glitch.detectivesearch.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.glitch.detectivesearch.data.model.response.CaseEntity
import com.glitch.detectivesearch.data.model.response.EvalEntity

@Dao
interface EvalDao {
	@Query("SELECT * FROM eval")
	fun getEvaluations(): List<EvalEntity>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addEval(evalEntity: EvalEntity)

	@Delete
	fun deleteEval(evalEntity: EvalEntity)

	@Query("SELECT evalId from eval")
	fun getEvalIds(): List<Int>

	@Query("DELETE FROM eval")
	fun clearEval()

	@Update
	fun updateEvaluations(evalEntity: EvalEntity)
}