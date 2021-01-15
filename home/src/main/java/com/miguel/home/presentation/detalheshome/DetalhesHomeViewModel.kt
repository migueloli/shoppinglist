package com.miguel.home.presentation.detalheshome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetalhesHomeViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "Este é o DetalhesHomeFragment"
    }
    val text: LiveData<String> = _text
}