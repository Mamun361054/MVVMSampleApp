package com.example.mvvmsampleapp.ui.auth

import com.example.mvvmsampleapp.data.db.entities.User

interface AuthListener {
    fun started()
    fun success(user: User)
    fun failure(error : String)
}