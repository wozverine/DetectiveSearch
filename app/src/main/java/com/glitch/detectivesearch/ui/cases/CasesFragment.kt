package com.glitch.detectivesearch.ui.cases

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.glitch.detectivesearch.databinding.FragmentCasesBinding

class CasesFragment : Fragment() {
	private var _binding: FragmentCasesBinding? = null
	private val binding get() = _binding!!

	private val casesAdapter = CasesAdapter(
		onCaseClick =::onCaseClick
	)
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentCasesBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		with(binding){
			rvCases.adapter = casesAdapter

		}
	}

	private fun onCaseClick(id: Int) {
		Toast.makeText(requireContext(), id, Toast.LENGTH_SHORT).show()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}