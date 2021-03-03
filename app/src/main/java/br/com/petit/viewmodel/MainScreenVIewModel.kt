package br.com.petit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {
    private val _message = MutableLiveData("Hey you")
    val message: LiveData<String> = _message
}