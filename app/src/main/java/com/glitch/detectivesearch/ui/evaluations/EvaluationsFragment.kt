package com.glitch.detectivesearch.ui.evaluations

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.glitch.detectivesearch.databinding.FragmentEvaluationsBinding
import com.glitch.detectivesearch.ui.questions.QuestionsFragmentArgs
import com.glitch.detectivesearch.ui.questions.QuestionsFragmentDirections

class EvaluationsFragment : Fragment() {
	private var _binding: FragmentEvaluationsBinding? = null
	private val binding get() = _binding!!

	private lateinit var sharedPref: SharedPreferences

	private val args by navArgs<QuestionsFragmentArgs>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentEvaluationsBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

		var questionCount = 1

		with(binding) {
			fun setTexts() {
				val key = "eval_" + (args.caseId + 1) + "_q" + questionCount + "_array"
				val evaluations = getStringArrayResource(requireContext(), key)
				tvEval.text = evaluations[0]
				rbEval1.text = evaluations[1]
				rbEval2.text = evaluations[2]
				rbEval3.text = evaluations[3]
				questionCount += 1
			}
			setTexts()

			btnNextEval.setOnClickListener {
				if (questionCount == 3) {
					findNavController().navigate(
						QuestionsFragmentDirections.actionQuestionsFragmentToWinFragment(
							args.caseId
						)
					)
				}
				if (questionCount < 3) {
					setTexts()
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
