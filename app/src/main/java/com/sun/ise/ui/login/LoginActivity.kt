package com.sun.ise.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sun.ise.R
import com.sun.ise.data.local.LocalDataSource
import com.sun.ise.data.remote.RemoteDataSource
import com.sun.ise.data.remote.RetrofitService
import com.sun.ise.data.repository.UserRepository
import com.sun.ise.ui.main.MainActivity
import com.sun.ise.util.Algorithm
import com.sun.ise.util.SharePrefs
import com.sun.ise.util.StringUtils
import com.sun.ise.util.ViewModelUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val retrofit by lazy {
        RetrofitService.getService()
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
        setContentView(R.layout.activity_login)

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
                loginViewModel.login(email, Algorithm.md5(password))
                MainActivity.getIntent(this).apply {
                    startActivity(this)
                }
                finish()
            }
        }
    }
}
