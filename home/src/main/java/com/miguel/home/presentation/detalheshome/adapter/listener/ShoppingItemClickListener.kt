package com.miguel.home.presentation.detalheshome.adapter.listener

import com.miguel.home.presentation.presenter.ShoppingItemPresenter

class ShoppingItemClickListener(val clickListener: (id: Long) -> Unit) {
    fun onClick(shoppingItem: ShoppingItemPresenter) = clickListener(shoppingItem.id)
}