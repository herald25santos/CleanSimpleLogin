package herald.company.cleanapp.modules.dashboard

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import herald.company.cleanapp.R
import herald.company.cleanapp.common.BaseFragment
import herald.company.cleanapp.common.extensions.formatString
import herald.company.cleanapp.common.extensions.showToast
import herald.company.cleanapp.modules.login.AuthViewModel
import herald.company.cleanapp.modules.login.Error
import herald.company.cleanapp.modules.login.LogoutSuccess
import herald.company.cleanapp.modules.login.UserDetails
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by herald25santos on 2019-07-24
 */
class DashboardFragment : BaseFragment() {

    private val authViewModel by viewModel<AuthViewModel>()

    private lateinit var navigation: NavController

    override fun layoutId() = R.layout.fragment_dashboard

    override fun afterLayout(savedInstanceState: Bundle?) {
        super.afterLayout(savedInstanceState)
        initNavigation()
    }

    override fun beforeLayout(savedInstanceState: Bundle?) {
        super.beforeLayout(savedInstanceState)
        navigation = findNavController()
    }

    override fun onViewModelBound() {
        super.onViewModelBound()
        initAuthViewModel()
    }

    override fun onInitializeListener() {
        super.onInitializeListener()
        buttonLogout.setOnClickListener {
            authViewModel.logoutUser()
        }
    }

    private fun initAuthViewModel() {
        authViewModel.state.observe(this, Observer {
            when (it) {
                is LogoutSuccess -> {
                    initLogoutSuccess()
                }
                is UserDetails -> {
                    textViewWelcome.text =
                        formatString(R.string.param_welcome_name, it.auth.fullName)
                }
                is Error -> {
                    showToast(it.throwable.message)
                }
            }
        })
        authViewModel.getUserDetails()
    }

    private fun initLogoutSuccess() {
        val navBuilder = NavOptions.Builder()
        val navOptions = navBuilder.setPopUpTo(R.id.loginFragment, true).build()
        navigation.navigate(R.id.loginFragment, null, navOptions)
    }

    private fun initNavigation() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finish()
                }
            })
    }

    companion object {
        const val EXTRA_NAME = "name"
    }

}