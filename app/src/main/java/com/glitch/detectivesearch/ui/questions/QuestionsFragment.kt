package com.glitch.detectivesearch.ui.questions

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.databinding.FragmentQuestionsBinding
import kotlin.random.Random

class QuestionsFragment : Fragment() {
	private var _binding: FragmentQuestionsBinding? = null
	private val binding get() = _binding!!

	private lateinit var sharedPref: SharedPreferences

	private val args by navArgs<QuestionsFragmentArgs>()

	private lateinit var allCountryList: Array<String>
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentQuestionsBinding.inflate(inflater, container, false)
		allCountryList = resources.getStringArray(R.array.countries_array)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

		val flagMode = sharedPref.getBoolean("flagMode", true)
		val photoMode = sharedPref.getBoolean("photoMode", true)
		val easyMode = sharedPref.getBoolean("easyMode", true)

		var questionCount = 1

		allCountryList.drop(args.questionNumber)
		val firstThreeList = args.countries
		var correctCountry = ""

		with(binding) {
			fun setTexts(countryList: Array<String>) {
				correctCountry = countryList[0]
				val key: String = correctCountry + "_1"
				countryList.shuffle()

				if (easyMode) {
					tvQuestion.text = getStringResource(requireContext(), key)
				} else {
					val hardKey: String = correctCountry + "_2"
					tvQuestion.text = getStringResource(requireContext(), hardKey)
				}

				btnRadio1.text = countryList[0]
				btnRadio2.text = countryList[1]
				btnRadio3.text = countryList[2]

				if (flagMode) {
					ivCountry.setImageDrawable(getImageResource(requireContext(), key.lowercase()))
				} else {
					val flagKey: String = correctCountry + "_flag"
					ivCountry.setImageDrawable(
						getImageResource(
							requireContext(),
							flagKey.lowercase()
						)
					)
				}
			}
			if (!photoMode) ivCountry.isVisible = false

			setTexts(firstThreeList)

			btnTeleport.setOnClickListener {
				val radioButtonText = when (rgQuestions.checkedRadioButtonId) {
					R.id.btnRadio1 -> btnRadio1.text
					R.id.btnRadio2 -> btnRadio2.text
					else -> btnRadio3.text
				}
				if (radioButtonText.toString().lowercase() == correctCountry.lowercase()) {
					if (questionCount == 3) {
						Toast.makeText(requireContext(), "Won", Toast.LENGTH_SHORT).show()

						/*val navOptions = NavOptions.Builder()
							.setPopUpTo(R.id.filesFragment, false)
							.build()

						findNavController().navigate(QuestionsFragmentDirections.actionQuestionsFragmentToWinFragment(args.caseId), null, navOptions)*/

						findNavController().navigate(
							QuestionsFragmentDirections.actionQuestionsFragmentToWinFragment(
								args.caseId
							)
						)
					}
					if (questionCount < 3) {
						setTexts(getNewCountries())
						questionCount += 1
						Toast.makeText(requireContext(), "Correct Answer", Toast.LENGTH_SHORT)
							.show()
					}
				} else {
					Toast.makeText(requireContext(), "Wrong Answer", Toast.LENGTH_SHORT).show()
					val navOptions = NavOptions.Builder()
						.setPopUpTo(R.id.filesFragment, false)
						.build()

					findNavController().navigate(
						R.id.action_questionsFragment_to_failFragment,
						null,
						navOptions
					)

					//findNavController().navigate(R.id.action_questionsFragment_to_failFragment)
				}
				rgQuestions.clearCheck()
			}
		}
	}

	private fun getNewCountries(): Array<String> {
		val randomInts = generateSequence {
			Random.nextInt(allCountryList.size)
		}
			.distinct()
			.take(3)
			.toList()
		val newCountries = arrayOf(
			allCountryList[randomInts[0]],
			allCountryList[randomInts[1]],
			allCountryList[randomInts[2]]
		)
		allCountryList.drop(randomInts[0])
		return newCountries
	}

	private fun getStringResource(context: Context, name: String): String {
		return resources.getString(
			context.resources.getIdentifier(
				name, "string", context.packageName
			)
		)
	}

	private fun getImageResource(context: Context, name: String): Drawable? {
		return ContextCompat.getDrawable(
			context, context.resources.getIdentifier(
				name, "drawable", context.packageName
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

}