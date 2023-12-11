package com.glitch.detectivesearch.ui.cases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.data.model.response.Case
import com.glitch.detectivesearch.databinding.ItemCaseBinding

class CasesAdapter(
	private val onCaseClick: (Int) -> Unit
) : RecyclerView.Adapter<CasesAdapter.CasesViewHolder>() {

	private val caseList = mutableListOf<Case>()

	override fun onCreateViewHolder(
		parent: ViewGroup, viewType: Int
	): CasesAdapter.CasesViewHolder {
		val binding = ItemCaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return CasesViewHolder(binding, onCaseClick)
	}

	override fun onBindViewHolder(holder: CasesAdapter.CasesViewHolder, position: Int) {
		holder.bind(caseList[position])
	}

	override fun getItemCount(): Int {
		return caseList.size
	}

	class CasesViewHolder(
		private val binding: ItemCaseBinding, val onCaseClick: (Int) -> Unit
	) : RecyclerView.ViewHolder(binding.root) {
		fun bind(case: Case) {
			with(binding) {
				if (case.isCaseEnabled == "false") {
					ivFile.setImageResource(R.drawable.file_false)
				}
				if (case.isCaseEnabled == "true") {
					ivFile.setImageResource(R.drawable.file)
				}
				if (case.isCaseEnabled == "done") {
					ivFile.setImageResource(R.drawable.file_done)
				}
				tvProductName.text = case.caseName

				root.setOnClickListener {
					onCaseClick(case.id)
				}
			}
		}
	}
	fun updateList(list: List<Case>) {
		caseList.clear()
		caseList.addAll(list)
		notifyItemRangeChanged(0, list.size)
	}
}