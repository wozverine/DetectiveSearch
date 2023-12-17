package com.glitch.detectivesearch.ui.options

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.data.respository.CaseRepository
import com.glitch.detectivesearch.data.source.local.CaseRoomDB
import com.glitch.detectivesearch.databinding.FragmentOptionsBinding
import kotlinx.coroutines.launch

class OptionsFragment : Fragment() {
	private var _binding: FragmentOptionsBinding? = null
	private val binding get() = _binding!!

	private lateinit var sharedPref: SharedPreferences

	private lateinit var caseRepository: CaseRepository

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		_binding = FragmentOptionsBinding.inflate(inflater, container, false)

		sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

		binding.flagSwitch.isOn = sharedPref.getBoolean("flagMode", true)
		binding.photoSwitch.isOn = sharedPref.getBoolean("photoMode", true)
		binding.easySwitch.isOn = sharedPref.getBoolean("easyMode", true)

		Log.v(
			"caseFragment:flagMode:sharedPref",
			sharedPref.getBoolean("flagMode", true).toString()
		)
		Log.v(
			"caseFragment:photoMode:sharedPref",
			sharedPref.getBoolean("photoMode", true).toString()
		)
		Log.v(
			"caseFragment:easyMode:sharedPref",
			sharedPref.getBoolean("easyMode", true).toString()
		)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val caseDao = CaseRoomDB.getInstance(requireContext()).caseDao()
		caseRepository = CaseRepository(caseDao)
		sharedPref = requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)

		with(binding) {
			flagSwitch.setOnClickListener {
				with(sharedPref.edit()) {
					putBoolean("flagMode", flagSwitch.isOn.not())
					apply()
				}
			}
			photoSwitch.setOnClickListener {
				if (photoSwitch.isOn) {
					with(sharedPref.edit()) {
						putBoolean("photoMode", false)
						apply()
					}
				} else {
					with(sharedPref.edit()) {
						putBoolean("photoMode", true)
						apply()
					}
				}
			}
			easySwitch.setOnClickListener {
				with(sharedPref.edit()) {
					putBoolean("easyMode", easySwitch.isOn.not())
					apply()
				}
			}

			btnReset.setOnClickListener {
				sharedPref =
					requireActivity().getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
				with(sharedPref.edit()) {
					putBoolean("firstPlay", true)
					apply()
				}
				lifecycleScope.launch {
					caseRepository.clearCases()
				}
				findNavController().navigate(R.id.homeFragment)
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}