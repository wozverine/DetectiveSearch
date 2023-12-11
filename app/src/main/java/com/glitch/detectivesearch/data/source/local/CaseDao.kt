package com.glitch.detectivesearch.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glitch.detectivesearch.data.model.response.CaseEntity

@Dao
interface CaseDao {

	@Query("SELECT * FROM cases")
	fun getCases(): List<CaseEntity>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun addProduct(caseEntity: CaseEntity)

	@Delete
	fun deleteCase(caseEntity: CaseEntity)

	@Query("SELECT caseId FROM cases")
	fun getCaseIds(): List<Int>

	@Query("DELETE FROM cases")
	fun clearCases()
}