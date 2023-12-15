package com.glitch.detectivesearch.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
	private var _binding: FragmentHomeBinding? = null
	private val binding get() = _binding!!

	private lateinit var sharedPref: SharedPreferences

	private val caseCount = 10
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		_binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
		val firstTime = sharedPref.getBoolean("firstLaunch", true)

		if (firstTime) {
			sharedPreferenceFirst()
		}

		with(binding){
			btnOptions.setOnClickListener {
				findNavController().navigate(R.id.action_homeFragment_to_optionsFragment)
			}
			btnStart.setOnClickListener {
				findNavController().navigate(R.id.action_homeFragment_to_filesFragment)
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	private fun sharedPreferenceFirst(){
		with(sharedPref.edit()) {
			putBoolean("easyMode", true)
			putBoolean("photoMode", true)
			putBoolean("flagMode", true)
			putInt("caseCount", caseCount)
			putBoolean("firstLaunch", false)
			apply()
		}
	}
}