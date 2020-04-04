package com.example.mvvmsampleapp.ui.auth
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.data.db.entities.User
import com.example.mvvmsampleapp.databinding.ActivitySignupBinding
import com.example.mvvmsampleapp.ui.home.HomeActivity
import com.example.mvvmsampleapp.utils.hide
import com.example.mvvmsampleapp.utils.show
import com.example.mvvmsampleapp.utils.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity() , KodeinAware,AuthListener{

    override val kodein by kodein()
    private val factory :AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bindingViewModel : ActivitySignupBinding = DataBindingUtil.setContentView(this,R.layout.activity_signup)
        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
        bindingViewModel.authviewmodel = viewModel
        viewModel.authListener = this
    }

    override fun started() {
        progress_bar.show()
    }

    override fun success(user: User) {
        progress_bar.hide()
        Intent(this, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun failure(error: String) {
        progress_bar.hide()
        root_layout.snackbar(error)
    }

}
