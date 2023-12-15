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

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentQuestionsBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

		val flagMode = sharedPref.getBoolean("flagMode", true)
		val photoMode = sharedPref.getBoolean("photoMode", true)
		val easyMode = sharedPref.getBoolean("easyMode", true)

		var questionCount = 1

		val allCountryList = resources.getStringArray(R.array.countries_array)
		allCountryList.drop(args.questionNumber)
		val countryList = args.countries
		var correctCountry = ""

		with(binding) {
			fun setTexts(countryList: Array<String>) {
				correctCountry = countryList[0]
				val key: String = correctCountry + "_1"
				countryList.shuffle()

				tvQuestion.text = getStringResource(requireContext(), key)
				btnRadio1.text = countryList[0]
				btnRadio2.text = countryList[1]
				btnRadio3.text = countryList[2]
				ivCountry.setImageDrawable(getImageResource(requireContext(), key.lowercase()))
			}
			setTexts(countryList)

			btnTeleport.setOnClickListener {
				val radioButtonText = when (rgQuestions.checkedRadioButtonId) {
					R.id.btnRadio1 -> btnRadio1.text
					R.id.btnRadio2 -> btnRadio2.text
					else -> btnRadio3.text
				}
				if (radioButtonText.toString().lowercase() == correctCountry.lowercase()) {
					if (questionCount == 3) {
						Toast.makeText(requireContext(), "Won", Toast.LENGTH_SHORT).show()
						findNavController().navigate(R.id.action_questionsFragment_to_winFragment)
						//TODO call fragment_win
					}
					if (questionCount < 3) {
						val newCountryList = getNewCountries(countryList)
						setTexts(newCountryList)
						questionCount += 1
						Toast.makeText(requireContext(), "Correct Answer", Toast.LENGTH_SHORT)
							.show()
					}
				} else {
					Toast.makeText(requireContext(), "Wrong Answer", Toast.LENGTH_SHORT).show()
					findNavController().navigate(R.id.action_questionsFragment_to_failFragment)
					//TODO call fragment_fail
				}
				rgQuestions.clearCheck()
			}
		}
	}

	private fun getNewCountries(allCountryList: Array<String>): Array<String> {
		val randomInts = generateSequence {
			Random.nextInt(allCountryList.size)
		}
			.distinct()
			.take(3)
			.toList()
		var newCountries = arrayOf(
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