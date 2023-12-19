package com.glitch.detectivesearch.ui.files

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.glitch.detectivesearch.data.common.Resource
import com.glitch.detectivesearch.data.model.mappers.mapToCase
import com.glitch.detectivesearch.data.model.mappers.mapToCaseUI
import com.glitch.detectivesearch.data.model.mappers.mapToEval
import com.glitch.detectivesearch.data.model.mappers.mapToEvalUI
import com.glitch.detectivesearch.data.model.response.Case
import com.glitch.detectivesearch.data.model.response.Eval
import com.glitch.detectivesearch.data.respository.CaseRepository
import com.glitch.detectivesearch.data.respository.EvalRepository
import com.glitch.detectivesearch.data.source.local.CaseRoomDB
import com.glitch.detectivesearch.data.source.local.EvalRoomDB
import com.glitch.detectivesearch.databinding.FragmentFilesBinding
import kotlinx.coroutines.launch

class FilesFragment : Fragment() {
	private var _binding: FragmentFilesBinding? = null
	private val binding get() = _binding!!

	private lateinit var sharedPref: SharedPreferences

	private lateinit var caseRepository: CaseRepository
	private lateinit var evalRepository: EvalRepository

	private val casesAdapter = CasesAdapter(
		onCaseClick = ::onCaseClick
	)

	private val evalAdapter = EvalAdapter(
		onEvalClick = ::onEvalClick
	)

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		_binding = FragmentFilesBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

		val caseDao = CaseRoomDB.getInstance(requireContext()).caseDao()
		caseRepository = CaseRepository(caseDao)

		val evalDao = EvalRoomDB.getInstance(requireContext()).evalDao()
		evalRepository = EvalRepository(evalDao)

		val firstTime = sharedPref.getBoolean("firstPlay", true)
		val caseCount = sharedPref.getInt("caseCount", 0)

		if (firstTime) {
			val caseList: MutableList<Case> = mutableListOf()
			val evalList: MutableList<Eval> = mutableListOf()

			lifecycleScope.launch {
				caseList.add(0, Case(0, "Case 1", "true", "false"))
				evalList.add(0, Eval(0, "Eval 1", "false"))
				caseRepository.addToCases(caseList[0].mapToCaseUI())
				evalRepository.addToEvaluations(evalList[0].mapToEvalUI())

				for (x in 1 until caseCount) {
					caseList.add(x, Case(x, "Case " + (x + 1), "false", "false"))
					evalList.add(x, Eval(x, "Eval " + (x + 1), "false"))

					caseRepository.addToCases(caseList[x].mapToCaseUI())
					evalRepository.addToEvaluations(evalList[x].mapToEvalUI())
				}
				updateUI()
			}

			sharedPreferenceFirst()
		} else {
			lifecycleScope.launch {
				updateUI()
			}
		}

		lifecycleScope.launch {
			when (val resource = caseRepository.getCases()) {
				is Resource.Success -> {
					val casesUI = resource.data
					val cases = casesUI.map { it.mapToCase() }
					casesAdapter.updateList(cases)
				}

				is Resource.Error -> {
					// Handle the error state, if needed
				}

				else -> {}
			}

			when (val resource = evalRepository.getEvaluations()) {
				is Resource.Success -> {
					val evalUI = resource.data
					val eval = evalUI.map { it.mapToEval() }
					evalAdapter.updateList(eval)
				}

				is Resource.Error -> {
					// Handle the error state, if needed
				}

				else -> {}
			}
		}

		with(binding) {
			rvCases.adapter = casesAdapter
			rvEval.adapter = evalAdapter
		}
	}

	private suspend fun updateUI() {
		when (val resource = caseRepository.getCases()) {
			is Resource.Success -> {
				val casesUI = resource.data
				val cases = casesUI.map { it.mapToCase() }
				casesAdapter.updateList(cases)
			}

			is Resource.Error -> {
				// Handle the error state, if needed
			}

			else -> {}
		}

		when (val resource = evalRepository.getEvaluations()) {
			is Resource.Success -> {
				val evalUI = resource.data
				val eval = evalUI.map { it.mapToEval() }
				evalAdapter.updateList(eval)
			}

			is Resource.Error -> {
				// Handle the error state, if needed
			}

			else -> {}
		}
	}


	private fun onCaseClick(id: Int) {
		lifecycleScope.launch {
			var isEnabled = "false"
			when (val result = caseRepository.getCases()) {
				is Resource.Success -> isEnabled = result.data[id].isCaseEnabled
				is Resource.Fail -> result.failMessage
				is Resource.Error -> result.errorMessage
			}
			if (isEnabled == "false") {
				val toasty = "You haven't unlocked this case yet"
				Toast.makeText(requireContext(), toasty, Toast.LENGTH_SHORT).show()
			}
			if (isEnabled == "true") {
				findNavController().navigate(
					FilesFragmentDirections.actionFilesFragmentToStoryFragment(id)
				)
			}
			if (isEnabled == "done") {
				val toasty = "You have already solved this case"
				Toast.makeText(requireContext(), toasty, Toast.LENGTH_SHORT).show()
			}
		}
	}

	private fun onEvalClick(id: Int) {
		lifecycleScope.launch {
			var isEnabled = "false"
			when (val result = evalRepository.getEvaluations()) {
				is Resource.Success -> isEnabled = result.data[id].isEvalEnabled
				is Resource.Fail -> result.failMessage
				is Resource.Error -> result.errorMessage
			}
			if (isEnabled == "false") {
				val toasty = "You haven't unlocked this evaluation yet"
				Toast.makeText(requireContext(), toasty, Toast.LENGTH_SHORT).show()
			}
			if (isEnabled == "true") {
				findNavController().navigate(
					FilesFragmentDirections.actionFilesFragmentToEvaluationsFragment(id)
				)
			}
			if (isEnabled == "done") {
				val toasty = "You have already solved this evaluation"
				Toast.makeText(requireContext(), toasty, Toast.LENGTH_SHORT).show()
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private fun sharedPreferenceFirst() {
		with(sharedPref.edit()) {
			putBoolean("firstPlay", false)
			apply()
		}
	}
}