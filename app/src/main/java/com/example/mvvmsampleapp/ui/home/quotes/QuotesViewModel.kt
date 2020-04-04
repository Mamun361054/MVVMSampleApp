package com.example.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repositories.QuotesRepository
import com.example.mvvmsampleapp.utils.lazyDeferred

class QuotesViewModel(repository: QuotesRepository) : ViewModel() {

    val quotes by lazyDeferred() {
        repository.getQuotes()
    }

}
