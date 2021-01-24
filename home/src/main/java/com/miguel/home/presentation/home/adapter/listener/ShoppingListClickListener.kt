package com.miguel.home.presentation.home.adapter.listener

import com.miguel.home.presentation.presenter.ShoppingListPresenter

class ShoppingListClickListener (val clickListener: (id: Long) -> Unit, val longClickListener: (id: Long) -> Boolean) {
    fun onClick(shoppingList: ShoppingListPresenter) = clickListener(shoppingList.id)
    fun onLongClick(shoppingList: ShoppingListPresenter): Boolean = longClickListener(shoppingList.id)
}