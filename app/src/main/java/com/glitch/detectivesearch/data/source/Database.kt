package com.glitch.detectivesearch.data.source

import com.glitch.detectivesearch.data.model.Case

object Database {
	private val caseList = mutableListOf<Case>()

	fun getCases(): List<Case>{
		return caseList
	}

	fun addCases(caseName:String, desc:String){
		val newCase= Case(
			id = (caseList.lastOrNull()?.id ?: 0) + 1,
			caseName = caseName,
			desc = desc
		)
		caseList.add(newCase)
	}
}