package com.sun.ise.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.sun.ise.R
import com.sun.ise.data.local.LocalDataSource
import com.sun.ise.data.remote.RemoteDataSource
import com.sun.ise.data.remote.RetrofitService
import com.sun.ise.data.repository.UserRepository
import com.sun.ise.ui.common.LoginCallback
import com.sun.ise.ui.main.MainActivity
import com.sun.ise.util.Algorithm
import com.sun.ise.util.SharePrefs
import com.sun.ise.util.StringUtils
import com.sun.ise.util.ViewModelUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginCallback {

    private val retrofit by lazy {
        RetrofitService.getInstance(application).getService()
    }
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProviders.of(
            this,
            ViewModelUtil.viewModelFactory {
                LoginViewModel(
                    this,
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
            } else if (!StringUtils.checkNotEmpty(email)) {
                showToast(getString(R.string.toast_empty_email))
            } else if (!StringUtils.checkNotEmpty(password)) {
                showToast(getString(R.string.toast_empty_password))
            }
        }
    }

    override fun onSuccess() {
        MainActivity.getIntent(this).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(this)
        }
        finish()
    }

    override fun onInvalidEmailOrPassword() {
        showToast(getString(R.string.toast_login_failed))
    }

    override fun onError(exception: Exception) {
        showToast(getString(R.string.toast_server_error))
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}
