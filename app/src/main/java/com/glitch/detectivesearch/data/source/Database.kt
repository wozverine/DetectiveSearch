package com.glitch.detectivesearch.data.source

import com.glitch.detectivesearch.data.model.Case

object Database {
	private val caseList = mutableListOf<Case>()

	fun getCases(): List<Case> {
		return caseList
	}

	fun addCases(caseName: String, isCaseEnabled: String, isEvalEnabled: String) {
		val newCase = Case(
			id = (caseList.lastOrNull()?.id ?: 0) + 1,
			caseName = caseName,
			isCaseEnabled = isCaseEnabled,
			isEvalEnabled = isEvalEnabled
		)
		caseList.add(newCase)
	}
}