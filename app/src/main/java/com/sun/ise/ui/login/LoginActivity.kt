package com.sun.ise.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sun.ise.data.local.LocalDataSource
import com.sun.ise.data.remote.RemoteDataSource
import com.sun.ise.data.remote.RetrofitService
import com.sun.ise.data.repository.UserRepository
import com.sun.ise.ui.main.MainActivity
import com.sun.ise.util.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val retrofit by lazy {
        RetrofitService.getInstance(application).getService()
    }
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(
            this,
            ViewModelUtil.viewModelFactory {
                LoginViewModel(
                    UserRepository(
                        LocalDataSource.getInstance(application),
                        RemoteDataSource(retrofit)
                    )
                )
            }
        ).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.sun.ise.R.layout.activity_login)

        val prefs = SharePrefs(application)
        val token = prefs.token
        if (!token.isNullOrBlank()) {
            MainActivity.getIntent(this).apply {
                startActivity(this)
            }
            finish()
        }

        buttonSignIn.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            if (StringUtils.checkNotEmpty(email, password)) {
                when (loginViewModel.login(email, Algorithm.md5(password))) {
                    Constants.CODE_OK -> {
                        MainActivity.getIntent(this).apply {
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                                Intent.FLAG_ACTIVITY_CLEAR_TASK or
                                Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(this)
                        }
                        finish()
                    }
                    Constants.CODE_NOT_FOUND -> {
                        showToast("Wrong email or password!")
                    }
                    Constants.CODE_SERVER_ERROR -> {
                        showToast("Something is wrong, please try again!")
                    }
                }

            } else if (!StringUtils.checkNotEmpty(email)) {
                showToast("Please enter email!")
            } else if (!StringUtils.checkNotEmpty(password)) {
                showToast("Please enter password!")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}
