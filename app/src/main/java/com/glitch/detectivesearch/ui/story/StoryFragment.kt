package com.glitch.detectivesearch.ui.story

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.data.common.Resource
import com.glitch.detectivesearch.data.respository.CaseRepository
import com.glitch.detectivesearch.data.source.local.CaseRoomDB
import com.glitch.detectivesearch.databinding.FragmentStoryBinding
import kotlinx.coroutines.launch
import java.util.Arrays
import kotlin.random.Random

class StoryFragment : Fragment() {
	private var _binding: FragmentStoryBinding? = null
	private val binding get() = _binding!!

	private val args by navArgs<StoryFragmentArgs>()
	private lateinit var caseRepository: CaseRepository
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentStoryBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		Log.v("StoryFragment", args.id.toString())
		val caseDao = CaseRoomDB.getInstance(requireContext()).caseDao()
		caseRepository = CaseRepository(caseDao)

		lifecycleScope.launch {
			var toasty = "false"
			when (val result = caseRepository.getCases()) {
				is Resource.Success -> toasty = result.data[args.id].caseName
				is Resource.Fail -> result.failMessage
				is Resource.Error -> result.errorMessage
			}
			//Toast.makeText(requireContext(), toasty, Toast.LENGTH_SHORT).show()
		}

		val countryList = resources.getStringArray(R.array.countries_array)

		val randomInts = generateSequence {
			Random.nextInt(countryList.size)
		}
			.distinct()
			.take(3)
			.toList()

		val chosenCountries: Array<String> = arrayOf(countryList[randomInts[0]],countryList[randomInts[1]],countryList[randomInts[2]])

		with(binding) {
			tvStory.text = getStringResource(requireContext(), ("story_"+(args.id+1)))
			btnContinue.setOnClickListener {
			}
			findNavController().navigate(StoryFragmentDirections.actionStoryFragmentToQuestionsFragment(chosenCountries,randomInts[0]))
		}
	}

	private fun getStringResource(context: Context, name: String): String {
		return resources.getString(
			context.resources.getIdentifier(
				name, "string", context.packageName
			)
		)
	}
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}