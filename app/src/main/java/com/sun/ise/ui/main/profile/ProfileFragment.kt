package com.sun.ise.ui.main.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sun.ise.R
import com.sun.ise.data.local.LocalDataSource
import com.sun.ise.data.model.User
import com.sun.ise.data.remote.RemoteDataSource
import com.sun.ise.data.remote.RetrofitService
import com.sun.ise.data.repository.UserRepository
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
        bindData(user)
    }

    private fun bindData(user: User) {
        textName.text = user.name
        textAccountType.text = user.type
        textRollNumber.text = user.code
        textMajor.text = user.majorId.toString()
        textDateOfBirth.text = StringUtils.simplifyDate(user.dateOfBirth)
        textGender.text = StringUtils.getGender(user.gender)
        textEmail.text = user.email
        textPhone.text = user.phone
    }
}
