package com.miguel.home.presentation.detalheshome.adapter.listener

import androidx.recyclerview.widget.DiffUtil
import com.miguel.home.presentation.presenter.ShoppingItemPresenter

class ShoppingItemDiffCallback: DiffUtil.ItemCallback<ShoppingItemPresenter>() {

    override fun areItemsTheSame(oldItem: ShoppingItemPresenter, newItem: ShoppingItemPresenter): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShoppingItemPresenter, newItem: ShoppingItemPresenter): Boolean {
        return oldItem == newItem
    }

}