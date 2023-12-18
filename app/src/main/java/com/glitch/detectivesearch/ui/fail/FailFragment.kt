package com.glitch.detectivesearch.ui.fail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.databinding.FragmentFailBinding

class FailFragment : Fragment() {
	private var _binding: FragmentFailBinding? = null
	private val binding get() = _binding!!
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentFailBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		with(binding) {
			btnFail.setOnClickListener {
				findNavController().popBackStack(R.id.filesFragment,false)
				findNavController().navigate(R.id.homeFragment)
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}