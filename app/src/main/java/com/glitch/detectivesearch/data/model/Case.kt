package com.glitch.detectivesearch.data.model

data class Case(
	val id: Int,
	val caseName: String,
	val isCaseEnabled: String = "false",
	val isEvalEnabled: String = "false"
)
