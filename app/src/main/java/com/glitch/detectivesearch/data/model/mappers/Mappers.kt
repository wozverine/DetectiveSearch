package com.glitch.detectivesearch.data.model.mappers

import com.glitch.detectivesearch.data.model.response.Case
import com.glitch.detectivesearch.data.model.response.CaseEntity
import com.glitch.detectivesearch.data.model.response.CaseUI


fun Case.mapToCaseUI() = CaseUI(
	id = id ?: 1,
	caseName = caseName.orEmpty(),
	isCaseEnabled = isCaseEnabled.orEmpty(),
	isEvalEnabled = isEvalEnabled.orEmpty()
)

fun List<Case>.mapCaseToCaseUI() = map {
	CaseUI(
		id = it.id ?: 1,
		caseName = it.caseName.orEmpty(),
		isCaseEnabled = it.isCaseEnabled.orEmpty(),
		isEvalEnabled = it.isEvalEnabled.orEmpty()
	)
}

fun CaseUI.mapToCaseEntity() = CaseEntity(
	productId = id,
	caseName = caseName,
	isCaseEnabled = isCaseEnabled,
	isEvalEnabled = isEvalEnabled
)

fun List<CaseEntity>.mapCaseEntityToCaseUI() = map {
	CaseUI(
		id = it.productId ?: 1,
		caseName = it.caseName.orEmpty(),
		isCaseEnabled = it.isCaseEnabled.orEmpty(),
		isEvalEnabled = it.isEvalEnabled.orEmpty()
	)
}
