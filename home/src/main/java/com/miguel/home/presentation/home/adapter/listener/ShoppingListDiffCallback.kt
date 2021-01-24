package com.miguel.home.presentation.home.adapter.listener

import androidx.recyclerview.widget.DiffUtil
import com.miguel.home.presentation.presenter.ShoppingListPresenter

class ShoppingListDiffCallback: DiffUtil.ItemCallback<ShoppingListPresenter>() {

    override fun areItemsTheSame(oldItem: ShoppingListPresenter, newItem: ShoppingListPresenter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShoppingListPresenter, newItem: ShoppingListPresenter): Boolean {
        return oldItem == newItem
    }

}