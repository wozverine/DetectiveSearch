package com.glitch.detectivesearch.ui.win

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.data.respository.CaseRepository
import com.glitch.detectivesearch.data.respository.EvalRepository
import com.glitch.detectivesearch.data.source.local.CaseRoomDB
import com.glitch.detectivesearch.data.source.local.EvalRoomDB
import com.glitch.detectivesearch.databinding.FragmentWinBinding
import kotlinx.coroutines.launch

class WinFragment : Fragment() {
	private var _binding: FragmentWinBinding? = null
	private val binding get() = _binding!!

	private lateinit var caseRepository: CaseRepository
	private lateinit var evalRepository: EvalRepository

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
				val evalDao = EvalRoomDB.getInstance(requireContext()).evalDao()
				caseRepository = CaseRepository(caseDao)
				evalRepository = EvalRepository(evalDao)
				lifecycleScope.launch {
					caseRepository.updateCase(args.id, "done")
					evalRepository.updateEvaluations(args.id, "true")
					findNavController().popBackStack(R.id.filesFragment, false)
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