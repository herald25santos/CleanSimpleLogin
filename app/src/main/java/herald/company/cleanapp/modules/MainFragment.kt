package herald.company.cleanapp.modules

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import herald.company.cleanapp.R
import herald.company.cleanapp.common.BaseFragment
import herald.company.cleanapp.common.extensions.showToast
import herald.company.cleanapp.modules.login.AuthViewModel
import herald.company.cleanapp.modules.login.Error
import herald.company.cleanapp.modules.login.IsLoggedIn
import herald.company.cleanapp.modules.login.LoginSuccess
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by herald25santos on 2019-07-24
 */
class MainFragment : BaseFragment() {

    private val authViewModel by viewModel<AuthViewModel>()

    override fun layoutId() = R.layout.fragment_main

    override fun onViewModelBound() {
        super.onViewModelBound()
        initAuthViewModel()
    }

    private fun initAuthViewModel() {
        authViewModel.state.observe(this, Observer {
            when (it) {
                is IsLoggedIn -> {
                    if (it.isLoggedIn) {
                        findNavController().navigate(R.id.dashboardFragment)
                    } else {
                        findNavController().navigate(R.id.loginFragment)
                    }
                }
                is Error -> {
                    showToast(it.throwable.message)
                }
            }
        })
        authViewModel.isLoggedIn()
    }

}