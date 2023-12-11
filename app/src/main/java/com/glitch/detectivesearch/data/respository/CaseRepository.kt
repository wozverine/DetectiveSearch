package com.glitch.detectivesearch.data.respository

import com.glitch.detectivesearch.data.model.mappers.mapCaseEntityToCaseUI
import com.glitch.detectivesearch.data.model.mappers.mapToCaseEntity
import com.glitch.detectivesearch.data.model.response.CaseUI
import com.glitch.detectivesearch.data.source.local.CaseDao

class CaseRepository(
	private val caseDao: CaseDao
) {
	fun addToCases(caseUI: CaseUI) {
		caseDao.addProduct(caseUI.mapToCaseEntity())
	}

	fun deleteFromCases(caseUI: CaseUI) {
		caseDao.deleteProduct(caseUI.mapToCaseEntity())
	}

	fun getCases(): List<CaseUI> {
		val cases = caseDao.getCases()
		return cases.mapCaseEntityToCaseUI()
	}

	fun clearFavorites() {
		caseDao.clearFavorites()
	}

}
