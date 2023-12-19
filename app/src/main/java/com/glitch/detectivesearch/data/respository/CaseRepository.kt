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
		caseDao.addCase(caseUI.mapToCaseEntity())
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

	/*suspend fun updateCase(caseId: Int, updatedCase: Case): Resource<Unit> {
		return try {
			// Assuming you have a function to update the case in your data source
			caseDao.updateCase(caseId, updatedCase)
			Resource.Success(Unit)
		} catch (e: Exception) {
			// Handle the error appropriately
			Resource.Error("Failed to update the case: ${e.message}")
		}
	}*/

	suspend fun updateCase(caseId: Int, changeCase: String): Resource<Unit> = withContext(Dispatchers.IO) {
		try {
			val case = caseDao.getCases().find { it.caseId == caseId }
			if (case != null) {
				val updatedCaseEntity = case.copy(
					isCaseEnabled = changeCase
				)
				caseDao.updateCase(updatedCaseEntity)
				Resource.Success(Unit)
			} else {
				Resource.Error("Case not found")
			}
		} catch (e: Exception) {
			Resource.Error("Failed to update the case: ${e.message}")
		}
	}
}
