package com.example.mvvmsampleapp.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repositories.UserRepository
import com.example.mvvmsampleapp.ui.home.HomeActivity
import com.example.mvvmsampleapp.utils.ApiExceptions
import com.example.mvvmsampleapp.utils.Coroutines
import com.example.mvvmsampleapp.utils.NoInternetAvailable

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null
    var authListener: AuthListener? = null

    fun getLoggedInUser() = repository.getUser()

    fun authLoginButtonClick(view: View) {
        authListener?.started()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListener?.failure("Invalid email or password")
            return
        }
        Coroutines.main() {
            try {
                val response = repository.userLogin(email!!, password!!)
                response.user?.let {
                    authListener?.success(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.failure(response.message!!)
            } catch (e: ApiExceptions) {
                authListener?.failure(e.message!!)
            } catch (e: NoInternetAvailable) {
                authListener?.failure(e.message!!)
            }
        }
    }

    fun gotoSingupPage(view: View){
        Intent(view.context,SignupActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            view.context.startActivity(it)
        }
    }

    fun gotoSinginPage(view: View){
        Intent(view.context,LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            view.context.startActivity(it)
        }
    }

    fun authSignupButtonCLick(view: View) {

        authListener?.started()

        if (name.isNullOrEmpty()) {
            authListener?.failure("Name is empty")
            return
        }

        if (email.isNullOrEmpty()) {
            authListener?.failure("Email is empty")
            return
        }
        if (password.isNullOrEmpty()) {
            authListener?.failure("Password is empty")
            return
        }
        if (confirmPassword.isNullOrEmpty()) {
            authListener?.failure("Confirm Password is empty")
            return
        }

        if (!password.equals(confirmPassword)) {
            authListener?.failure("Confirm Password does not match")
            return
        }

        Coroutines.main(){
            try {
                val response = repository.userSignup(name!!,email!!,password!!)
                response.user?.let {
                    authListener?.success(it)
                    repository.saveUser(it)
                    return@main
                }
            } catch (e:ApiExceptions) {
                authListener?.failure(e.message!!)
            }    catch (e:NoInternetAvailable){
                authListener?.failure(e.message!!)
            }
        }

    }


}