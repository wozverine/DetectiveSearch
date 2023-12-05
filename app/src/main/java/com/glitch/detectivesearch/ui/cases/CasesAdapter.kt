package com.glitch.detectivesearch.ui.cases

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glitch.detectivesearch.data.model.Case

class CasesAdapter(
	private val onCaseClick: (Int) -> Unit
) : RecyclerView.Adapter<CasesAdapter.CasesViewHolder>() {
	private val caseList = mutableListOf<Case>()
	override fun onCreateViewHolder(
		parent: ViewGroup, viewType: Int
	): CasesAdapter.CasesViewHolder {
		TODO("Not yet implemented")
	}

	override fun onBindViewHolder(holder: CasesAdapter.CasesViewHolder, position: Int) {
		TODO("Not yet implemented")
	}

	override fun getItemCount(): Int {
		TODO("Not yet implemented")
	}

	class CasesViewHolder(
		private val binding: ItemCaseBinding, val onCaseClick: (Int) -> Unit
	) : RecyclerView.ViewHolder(binding.root) {

	}
}