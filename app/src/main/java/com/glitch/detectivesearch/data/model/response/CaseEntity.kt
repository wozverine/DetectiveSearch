package com.glitch.detectivesearch.data.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cases")
data class CaseEntity(
	@PrimaryKey @ColumnInfo(name = "caseId") val caseId: Int?,

	@ColumnInfo(name = "caseName") val caseName: String?,

	@ColumnInfo(name = "isCaseEnabled") val isCaseEnabled: String?,

	@ColumnInfo(name = "isEvalEnabled") val isEvalEnabled: String?
)
