package com.glitch.detectivesearch.data.model.response

data class Case(
	val id: Int,
	var caseName: String? = null,
	var isCaseEnabled: String? = "false",
	var isEvalEnabled: String? = "false"
)
