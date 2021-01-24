package com.miguel.home.presentation.detalheshome.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miguel.core.extension.converterParaString
import com.miguel.core.extension.converterParaStringData
import com.miguel.home.databinding.ListItemShoppingItemBinding
import com.miguel.home.presentation.detalheshome.adapter.listener.ShoppingItemClickListener
import com.miguel.home.presentation.detalheshome.adapter.listener.ShoppingItemDiffCallback
import com.miguel.home.presentation.presenter.ShoppingItemPresenter

class ShoppingItemAdapter(
    private val clickListener: ShoppingItemClickListener
): ListAdapter<ShoppingItemPresenter, ShoppingItemAdapter.ViewHolder>(ShoppingItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), clickListener)

    class ViewHolder private constructor(private val binding: ListItemShoppingItemBinding): RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ListItemShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: ShoppingItemPresenter, clickListener: ShoppingItemClickListener) =
            binding.run {
                descricao.text = item.descricao
                ultimaAlteracao.text = item.ultimaAlteracao.converterParaStringData()
                val qtde = "Qtde: ${item.quantidade.converterParaString()}"
                quantidade.text = qtde
                root.setOnClickListener { clickListener.onClick(item) }
            }

    }

}