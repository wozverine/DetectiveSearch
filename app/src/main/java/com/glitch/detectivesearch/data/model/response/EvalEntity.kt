package com.glitch.detectivesearch.data.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "eval")
data class EvalEntity(
	@PrimaryKey @ColumnInfo(name = "evalId") val evalId: Int?,

	@ColumnInfo(name = "evalName") val evalName: String?,

	@ColumnInfo(name = "isEvalEnabled") val isEvalEnabled: String?
)
