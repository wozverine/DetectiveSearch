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
import com.glitch.detectivesearch.data.common.Resource
import com.glitch.detectivesearch.data.model.mappers.mapToCase
import com.glitch.detectivesearch.data.model.mappers.mapToCaseUI
import com.glitch.detectivesearch.data.model.response.Case
import com.glitch.detectivesearch.data.respository.CaseRepository
import com.glitch.detectivesearch.data.source.local.CaseRoomDB
import com.glitch.detectivesearch.databinding.FragmentCasesBinding
import kotlinx.coroutines.launch

class CasesFragment() : Fragment() {
	private var _binding: FragmentCasesBinding? = null
	private val binding get() = _binding!!

	private lateinit var sharedPref: SharedPreferences

	private lateinit var caseRepository: CaseRepository

	private val casesAdapter = CasesAdapter(
		onCaseClick = ::onCaseClick
	)

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		_binding = FragmentCasesBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

		val caseDao = CaseRoomDB.getInstance(requireContext()).caseDao()
		caseRepository = CaseRepository(caseDao)

		val firstTime = sharedPref.getBoolean("firstPlay", true)
		val caseCount = sharedPref.getInt("caseCount", 0)

		if (firstTime) {
			val caseList: MutableList<Case> = mutableListOf()
			//val caseInfoList: MutableList<CaseInfo> = mutableListOf()

			fun getKey(caseNumber: Int, questionNumber: Int): String {
				return "eval_" + (caseNumber + 1) + "_q" + questionNumber + "_array"
			}

			for (x in 0..caseCount) {
				caseList.add(x, Case(x, "Case $x", "false", "false"))
				caseList[0].isCaseEnabled = "true"
				lifecycleScope.launch {
					caseRepository.addToCases(caseList[x].mapToCaseUI())
				}
			}

			/*for (x in 0..<caseCount) {
				val key = "story_" + (x + 1)
				caseInfoList.add(
					x, CaseInfo(
						x,
						getStringResource(requireContext(), key),
						getArrayListResource(getKey(x, 1), sharedPref),
						getArrayListResource(getKey(x, 2), sharedPref),
						getArrayListResource(getKey(x, 3), sharedPref)
					)
				)
			}*/
			sharedPreferenceFirst()
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
		}

		with(binding) {
			rvCases.adapter = casesAdapter
		}
	}

	private fun onCaseClick(id: Int) {
		Toast.makeText(requireContext(), id.toString(), Toast.LENGTH_SHORT).show()
	}

	/*fun loadData(key: String?): Int {
		val sharedPref = activity?.getSharedPreferences(
			getString(R.string.key), Context.MODE_PRIVATE)
		//return prefs.getInt(KEY, 0)
	}*/

	private fun getStringResource(context: Context, name: String): String {
		return resources.getString(
			context.resources.getIdentifier(
				name, "string", context.packageName
			)
		)
	}

	private fun getStringArrayResource(context: Context, name: String): Array<String> {
		val resourceId = context.resources.getIdentifier(name, "array", context.packageName)
		return context.resources.getStringArray(resourceId)
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private fun sharedPreferenceFirst(){
		with(sharedPref.edit()) {
			putBoolean("firstPlay", false)
			apply()
		}
	}
}