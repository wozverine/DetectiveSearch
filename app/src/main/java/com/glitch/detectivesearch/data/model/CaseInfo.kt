package com.glitch.detectivesearch.data.model

data class CaseInfo(
	val id: Int,
	val story: String = "",
	val question1: List<String>,
	val question2: List<String>,
	val question3: List<String>

)
