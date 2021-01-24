package com.miguel.home.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miguel.core.domain.model.ShoppingListModel
import com.miguel.core.extension.converterParaStringData
import com.miguel.home.databinding.ListItemShoppingListBinding
import com.miguel.home.presentation.home.adapter.listener.ShoppingListClickListener
import com.miguel.home.presentation.home.adapter.listener.ShoppingListDiffCallback
import com.miguel.home.presentation.presenter.ShoppingListPresenter

class ShoppingListAdapter(
    private val clickListener: ShoppingListClickListener
): ListAdapter<ShoppingListPresenter, ShoppingListAdapter.ViewHolder>(ShoppingListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), clickListener)

    class ViewHolder private constructor(private val binding: ListItemShoppingListBinding): RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ListItemShoppingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: ShoppingListPresenter, clickListener: ShoppingListClickListener) =
            binding.run {
                descricao.text = item.descricao
                ultimaAlteracao.text = item.ultimaAlteracao.converterParaStringData()
                root.setOnClickListener { clickListener.onClick(item) }
                root.setOnLongClickListener { clickListener.onLongClick(item) }
            }

    }

}
