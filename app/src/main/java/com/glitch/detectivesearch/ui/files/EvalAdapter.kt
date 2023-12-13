package com.glitch.detectivesearch.ui.files

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.glitch.detectivesearch.R
import com.glitch.detectivesearch.data.model.response.Eval
import com.glitch.detectivesearch.databinding.ItemEvalBinding

class EvalAdapter(
	private val onEvalClick: (Int) -> Unit
) : RecyclerView.Adapter<EvalAdapter.EvalViewHolder>() {

	private val evalList = mutableListOf<Eval>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvalViewHolder {
		val binding = ItemEvalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return EvalViewHolder(binding, onEvalClick)
	}

	override fun getItemCount(): Int {
		return evalList.size
	}

	override fun onBindViewHolder(holder: EvalViewHolder, position: Int) {
		return holder.bind(evalList[position])
	}

	class EvalViewHolder(
		private val binding: ItemEvalBinding, val onEvalClick: (Int) -> Unit
	) : RecyclerView.ViewHolder(binding.root) {
		fun bind(eval: Eval) {
			with(binding) {
				if (eval.isEvalEnabled == "false") {
					ivEval.setImageResource(R.drawable.file_false)
				}
				if (eval.isEvalEnabled == "true") {
					ivEval.setImageResource(R.drawable.file)
				}
				if (eval.isEvalEnabled == "done") {
					ivEval.setImageResource(R.drawable.file_done)
				}

				tvEvalName.text = eval.evalName

				root.setOnClickListener {
					onEvalClick(eval.id)
				}
			}
		}
	}

	fun updateList(list: List<Eval>) {
		evalList.clear()
		evalList.addAll(list)
		notifyItemRangeChanged(0, list.size)
	}
}