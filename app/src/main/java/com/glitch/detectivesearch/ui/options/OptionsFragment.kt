package com.glitch.detectivesearch.ui.options

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glitch.detectivesearch.databinding.FragmentOptionsBinding

class OptionsFragment : Fragment() {
	private var _binding: FragmentOptionsBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		_binding = FragmentOptionsBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

	}
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}