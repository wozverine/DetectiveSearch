package com.glitch.detectivesearch.ui.win

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.data.common.Resource
import com.glitch.detectivesearch.data.model.mappers.mapToCase
import com.glitch.detectivesearch.data.respository.CaseRepository
import com.glitch.detectivesearch.data.source.local.CaseRoomDB
import com.glitch.detectivesearch.databinding.FragmentWinBinding
import kotlinx.coroutines.launch

class WinFragment : Fragment() {
	private var _binding: FragmentWinBinding? = null
	private val binding get() = _binding!!

	private lateinit var caseRepository: CaseRepository

	private val args by navArgs<WinFragmentArgs>()
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentWinBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		with(binding) {
			btnWin.setOnClickListener {
				val caseDao = CaseRoomDB.getInstance(requireContext()).caseDao()
				caseRepository = CaseRepository(caseDao)
				lifecycleScope.launch {
					caseRepository.updateCase(args.id, "done")
					caseRepository.updateCase(args.id + 1, "true")
					findNavController().popBackStack(R.id.filesFragment,false)
					findNavController().navigate(R.id.filesFragment)
				}
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}