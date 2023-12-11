package com.glitch.detectivesearch.data.respository

import com.glitch.detectivesearch.data.common.Resource
import com.glitch.detectivesearch.data.model.mappers.mapCaseEntityToCaseUI
import com.glitch.detectivesearch.data.model.mappers.mapToCaseEntity
import com.glitch.detectivesearch.data.model.response.CaseUI
import com.glitch.detectivesearch.data.source.local.CaseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CaseRepository(
	private val caseDao: CaseDao
) {
	suspend fun addToCases(caseUI: CaseUI) = withContext(Dispatchers.IO) {
		caseDao.addProduct(caseUI.mapToCaseEntity())
	}

	suspend fun deleteFromCases(caseUI: CaseUI) = withContext(Dispatchers.IO) {
		caseDao.deleteCase(caseUI.mapToCaseEntity())
	}

	suspend fun getCases(): Resource<List<CaseUI>> = withContext(Dispatchers.IO) {
		try {
			val cases = caseDao.getCases()
			Resource.Success(cases.mapCaseEntityToCaseUI())
		} catch (e: Exception) {
			Resource.Error(e.message.orEmpty())
		}
	}

	suspend fun clearCases() = withContext(Dispatchers.IO) {
		caseDao.clearCases()
	}
}
