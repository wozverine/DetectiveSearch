package com.glitch.detectivesearch.data.respository

import com.glitch.detectivesearch.data.common.Resource
import com.glitch.detectivesearch.data.model.mappers.mapEvalEntityToEvalUI
import com.glitch.detectivesearch.data.model.mappers.mapToEvalEntity
import com.glitch.detectivesearch.data.model.response.EvalUI
import com.glitch.detectivesearch.data.source.local.EvalDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EvalRepository (
	private val evalDao: EvalDao
) {
	suspend fun addToEvaluations(evalUI: EvalUI) = withContext(Dispatchers.IO) {
		evalDao.addEval(evalUI.mapToEvalEntity())
	}

	suspend fun deleteFromEvaluations(evalUI: EvalUI) = withContext(Dispatchers.IO) {
		evalDao.deleteEval(evalUI.mapToEvalEntity())
	}

	suspend fun getEvaluations(): Resource<List<EvalUI>> = withContext(Dispatchers.IO) {
		try {
			val evaluations = evalDao.getEvaluations()
			Resource.Success(evaluations.mapEvalEntityToEvalUI())
		} catch (e: Exception) {
			Resource.Error(e.message.orEmpty())
		}
	}

	suspend fun clearEvaluations() = withContext(Dispatchers.IO) {
		evalDao.clearEval()
	}

	suspend fun updateEvaluations(evalId: Int, changeEval: String): Resource<Unit> = withContext(Dispatchers.IO) {
		try {
			val evaluation = evalDao.getEvaluations().find { it.evalId == evalId }
			if (evaluation != null) {
				var updatedEvalEntity = evaluation.copy(
					isEvalEnabled = changeEval
				)
				evalDao.updateEvaluations(updatedEvalEntity)
				Resource.Success(Unit)
			} else {
				Resource.Error("Evaluation not found")
			}
		} catch (e: Exception) {
			Resource.Error("Failed to update the evaluation: ${e.message}")
		}
	}
}