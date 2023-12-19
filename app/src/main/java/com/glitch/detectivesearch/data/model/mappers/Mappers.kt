package com.glitch.detectivesearch.data.model.mappers

import com.glitch.detectivesearch.data.model.response.Case
import com.glitch.detectivesearch.data.model.response.CaseEntity
import com.glitch.detectivesearch.data.model.response.CaseUI
import com.glitch.detectivesearch.data.model.response.Eval
import com.glitch.detectivesearch.data.model.response.EvalEntity
import com.glitch.detectivesearch.data.model.response.EvalUI


fun Case.mapToCaseUI() = CaseUI(
	id = id,
	caseName = caseName.orEmpty(),
	isCaseEnabled = isCaseEnabled.orEmpty(),
	isEvalEnabled = isEvalEnabled.orEmpty()
)

fun CaseUI.mapToCaseEntity() = CaseEntity(
	caseId = id,
	caseName = caseName,
	isCaseEnabled = isCaseEnabled,
	isEvalEnabled = isEvalEnabled
)

fun List<CaseEntity>.mapCaseEntityToCaseUI() = map {
	CaseUI(
		id = it.caseId ?: 1,
		caseName = it.caseName.orEmpty(),
		isCaseEnabled = it.isCaseEnabled.orEmpty(),
		isEvalEnabled = it.isEvalEnabled.orEmpty()
	)
}

fun CaseUI.mapToCase() = Case(
	id = id,
	caseName = caseName,
	isCaseEnabled = isCaseEnabled,
	isEvalEnabled = isEvalEnabled
)

fun Eval.mapToEvalUI() = EvalUI(
	id = id,
	evalName = evalName.orEmpty(),
	isEvalEnabled = isEvalEnabled.orEmpty()
)

fun EvalUI.mapToEvalEntity() = EvalEntity(
	evalId = id,
	evalName = evalName,
	isEvalEnabled = isEvalEnabled
)

fun List<EvalEntity>.mapEvalEntityToEvalUI() = map {
	EvalUI(
		id = it.evalId ?: 1,
		evalName = it.evalName.orEmpty(),
		isEvalEnabled = it.isEvalEnabled.orEmpty()
	)
}

fun EvalUI.mapToEval() = Eval(
	id = id,
	evalName = evalName,
	isEvalEnabled = isEvalEnabled
)
