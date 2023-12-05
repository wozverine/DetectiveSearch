package com.glitch.detectivesearch.ui.cases

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glitch.detectivesearch.data.model.Case
import com.glitch.detectivesearch.databinding.ItemCaseBinding

class CasesAdapter(
	private val onCaseClick: (Int) -> Unit
) : RecyclerView.Adapter<CasesAdapter.CasesViewHolder>() {
	private val caseList = mutableListOf<Case>()
	override fun onCreateViewHolder(
		parent: ViewGroup, viewType: Int
	): CasesAdapter.CasesViewHolder {
		val binding = ItemCaseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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
		fun bind(case: Case){
			with(binding){
				//ivFile.
				tvProductName.text = case.caseName

				root.setOnClickListener{
					//TODO
					//onCaseClick(case.desc)
				}
			}
		}
	}
}