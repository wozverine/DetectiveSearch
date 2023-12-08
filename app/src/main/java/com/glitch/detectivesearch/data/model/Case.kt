package com.glitch.detectivesearch.data.model

data class Case(
	val id: Int,
	var caseName: String? = null,
	var isCaseEnabled: String? = "false",
	var isEvalEnabled: String? = "false"
)
