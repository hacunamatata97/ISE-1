package com.sun.ise.ui.main.profile

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.sun.ise.R
import com.sun.ise.data.local.LocalDataSource
import com.sun.ise.data.model.UserWrapper
import com.sun.ise.data.remote.RemoteDataSource
import com.sun.ise.data.remote.RetrofitService
import com.sun.ise.data.repository.UserRepository
import com.sun.ise.ui.login.LoginActivity
import com.sun.ise.util.StringUtils
import com.sun.ise.util.ViewModelUtil
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(this, ViewModelUtil.viewModelFactory {
            ProfileViewModel(
                UserRepository(
                    LocalDataSource.getInstance(activity!!.application),
                    RemoteDataSource(RetrofitService.getInstance(activity!!.application).getService())
                )
            )
        }).get(ProfileViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.profile_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val user = viewModel.getCurrentUser()
        val positiveButtonClick = { dialog: DialogInterface, buttonId: Int ->
            LoginActivity.getIntent(activity!!).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
            }
            viewModel.logout()
        }
        bindData(user)
        buttonLogout.setOnClickListener {
            // val dialog = ConfirmDialog(positiveButtonClick)
            // dialog.show(fragmentManager, null)
            viewModel.logout()
            LoginActivity.getIntent(activity!!).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(this)
                activity?.finish()
            }
        }
    }

    private fun bindData(userWrapper: UserWrapper) {
        val user = userWrapper.user
        textName.text = user.name
        textAccountType.text = user.type
        textRollNumber.text = user.code
        textMajor.text = userWrapper.major.name
        textDateOfBirth.text = StringUtils.simplifyDate(user.dateOfBirth)
        textGender.text = StringUtils.getGender(user.gender)
        textEmail.text = user.email
        textPhone.text = user.phone
    }
}
