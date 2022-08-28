package com.toy.etwapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.toy.etwapp.ui.main.viewmodel.ContentsViewModel

class ContentsViewModelFactory(private val contentCnt: Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ContentsViewModel::class.java)){
            return ContentsViewModel(contentCnt) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}
