package com.glitch.detectivesearch.ui.questions

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.load.resource.drawable.DrawableResource
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.databinding.FragmentQuestionsBinding

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


		with(binding) {
			val countryList = args.countries
			val key: String = countryList[0] + "_1"
			tvQuestion.text = getStringResource(requireContext(), key)
			btnRadio1.text = countryList[0]
			btnRadio2.text = countryList[0]
			btnRadio3.text = countryList[0]
			ivCountry.setImageDrawable(getImageResource(requireContext(), key.lowercase()))
		}
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