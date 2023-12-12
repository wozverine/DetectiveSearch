package com.glitch.detectivesearch.ui.files

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glitch.detectivesearch.data.model.response.Eval
import com.glitch.detectivesearch.databinding.ItemCaseBinding

class EvalAdapter(
	private val onEvalClick: (Int) -> Unit
) : RecyclerView.Adapter<EvalAdapter.EvalViewHolder>() {

	private val evalList = mutableListOf<Eval>()


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvalViewHolder {
		val binding = ItemCaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return EvalAdapter.EvalViewHolder(binding, onEvalClick)
	}

	override fun getItemCount(): Int {
		return evalList.size
	}

	override fun onBindViewHolder(holder: EvalViewHolder, position: Int) {
		return holder.bind(evalList[position])
	}

	class EvalViewHolder(
		private val binding: ItemCaseBinding, val onEvalClick: (Int) -> Unit
	) : RecyclerView.ViewHolder(binding.root) {
		fun bind(eval: Eval) {

		}

	}
}