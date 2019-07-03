package com.sun.ise.ui.main.profile

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
import com.sun.ise.ui.common.MaterialDialog
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
        if (user != null) bindData(user)
        buttonLogout.setOnClickListener {
            showConfirmDialog()
        }
    }

    private fun showConfirmDialog() {
        val dialog = MaterialDialog(activity!!)
        dialog.apply {
            setMessage(R.string.confirm_message)
            setPositiveButton(R.string.confirm_positive_button, View.OnClickListener {
                viewModel.logout()
                LoginActivity.getIntent(activity!!).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(this)
                }
                activity!!.finish()
                this.dismiss()
            })
            setNegativeButton(R.string.confirm_negative_button, View.OnClickListener {
                this.dismiss()
            })
        }
        dialog.show()
    }

    private fun bindData(userWrapper: UserWrapper) {
        val user = userWrapper.user
        textName.text = user.name
        textAccountType.text = user.type
        textRollNumber.text = user.code
        textMajor.text = userWrapper.major.name
        textDateOfBirth.text = StringUtils.simplifyDate(user.dateOfBirth)
        textGender.text = user.getGender(activity!!)
        textEmail.text = user.email
        textPhone.text = user.phone
    }
}
