package com.example.mvvmsampleapp.data.network.responses

import com.example.mvvmsampleapp.data.db.entities.Quote

data class QuoteResponse(
    val isSuccessful : Boolean,
    val quotes : List<Quote>
)