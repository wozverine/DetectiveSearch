package com.glitch.detectivesearch.ui.cases

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.glitch.detectivesearch.data.model.Case
import com.glitch.detectivesearch.data.model.CaseInfo
import com.glitch.detectivesearch.databinding.FragmentCasesBinding

class CasesFragment : Fragment() {
	private var _binding: FragmentCasesBinding? = null
	private val binding get() = _binding!!

	private lateinit var sharedPref: SharedPreferences

	private val caseCount = 10

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

		/*val firstTime = sharedPref.getBoolean("firstTime", true)
		if (firstTime) {
			val caseList: MutableList<Case> = mutableListOf()
			val caseInfoList: MutableList<CaseInfo> = mutableListOf()

			fun getKey(caseNumber: Int, questionNumber: Int): String {
				return "eval_" + (caseNumber + 1) + "_q" + questionNumber + "_array"
			}

			for (x in 0..caseCount) {
				caseList.add(x, Case(x, "Case $x", "false", "false"))
			}

			for (x in 0..caseCount) {
				caseInfoList.add(
					x, CaseInfo(
						x,
						getStringResource(requireContext(), "story_" + (x + 1)),
						getArrayListResource(getKey(x, 1), sharedPref),
						getArrayListResource(getKey(x, 2), sharedPref),
						getArrayListResource(getKey(x, 3), sharedPref)
					)
				)
			}
			caseList[0].isCaseEnabled = "true"
			with(sharedPref.edit()) {
				putBoolean("firstTime", false)
				apply()
			}
		}*/

		with(binding) {
			rvCases.adapter = casesAdapter
		}
	}

	private fun onCaseClick(id: Int) {
		Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()
	}

	/*fun loadData(key: String?): Int {
		val sharedPref = activity?.getSharedPreferences(
			getString(R.string.key), Context.MODE_PRIVATE)
		//return prefs.getInt(KEY, 0)
	}*/

	private fun getArrayListResource(key: String, sharedPref: SharedPreferences): List<String> {
		val json = sharedPref.getString(key, null) ?: return emptyList()
		return listOf(json)
	}

	private fun getStringResource(context: Context, name: String): String {
		return resources.getString(
			context.resources.getIdentifier(
				name, "string", context.packageName
			)
		)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}