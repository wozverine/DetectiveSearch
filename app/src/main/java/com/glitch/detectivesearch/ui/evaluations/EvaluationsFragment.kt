package com.glitch.detectivesearch.ui.evaluations

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.data.respository.CaseRepository
import com.glitch.detectivesearch.data.respository.EvalRepository
import com.glitch.detectivesearch.data.source.local.CaseRoomDB
import com.glitch.detectivesearch.data.source.local.EvalRoomDB
import com.glitch.detectivesearch.databinding.FragmentEvaluationsBinding
import kotlinx.coroutines.launch

class EvaluationsFragment : Fragment() {
	private var _binding: FragmentEvaluationsBinding? = null
	private val binding get() = _binding!!

	private lateinit var sharedPref: SharedPreferences

	private lateinit var caseRepository: CaseRepository
	private lateinit var evalRepository: EvalRepository

	private val args by navArgs<EvaluationsFragmentArgs>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		_binding = FragmentEvaluationsBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

		var questionCount = 1

		val caseDao = CaseRoomDB.getInstance(requireContext()).caseDao()
		val evalDao = EvalRoomDB.getInstance(requireContext()).evalDao()
		caseRepository = CaseRepository(caseDao)
		evalRepository = EvalRepository(evalDao)

		with(binding) {
			fun setTexts() {
				val key = "eval_" + (args.evalId + 1) + "_q" + questionCount + "_array"
				val evaluations = getStringArrayResource(requireContext(), key)
				tvEval.text = evaluations[0]
				rbEval1.text = evaluations[1]
				rbEval2.text = evaluations[2]
				rbEval3.text = evaluations[3]
				questionCount += 1
			}
			setTexts()

			btnNextEval.setOnClickListener {
				if (questionCount == 4) {
					lifecycleScope.launch {
						caseRepository.updateCase(args.evalId + 1, "true")
						evalRepository.updateEvaluations(args.evalId, "done")
						val navOptions =
							NavOptions.Builder().setPopUpTo(R.id.filesFragment, false).build()

						findNavController().navigate(
							R.id.filesFragment, null, navOptions
						)
					}
				}
				if (questionCount < 4) {
					if (rgEval.checkedRadioButtonId != -1) {
						rgEval.clearCheck()
						setTexts()
					} else {
						Toast.makeText(
							requireContext(),
							getString(R.string.empty_answer),
							Toast.LENGTH_SHORT
						).show()
					}
				}
			}
		}
	}

	private fun getStringArrayResource(context: Context, name: String): Array<String> {
		val resourceId = context.resources.getIdentifier(name, "array", context.packageName)
		return context.resources.getStringArray(resourceId)
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}
