package herald.company.cleanapp.modules.login

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import herald.company.cleanapp.R
import herald.company.cleanapp.common.BaseFragment
import herald.company.cleanapp.common.extensions.showToast
import herald.company.cleanapp.entities.Auth
import herald.company.cleanapp.modules.dashboard.DashboardFragment
import herald.company.cleanapp.widgets.ProgressBarDialog
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


/**
 * Created by herald25santos on 2019-07-24
 */
class LoginFragment : BaseFragment() {

    private val authViewModel by viewModel<AuthViewModel>()

    private var progressDialog: ProgressBarDialog? = null

    override fun layoutId() = R.layout.fragment_login

    override fun onViewModelBound() {
        super.onViewModelBound()
        initAuthViewModel()
    }

    override fun afterLayout(savedInstanceState: Bundle?) {
        super.afterLayout(savedInstanceState)
        initNavigation()
    }

    override fun onInitializeListener() {
        super.onInitializeListener()
        buttonLogin.setOnClickListener {
            authViewModel.login(
                textInputEditTextUsername.text.toString(),
                textInputEditTextPassword.text.toString()
            )
        }
    }

    private fun initAuthViewModel() {
        authViewModel.state.observe(this, Observer {
            when (it) {
                is Loading -> {
                    progressDialog = ProgressBarDialog.newInstance()
                    progressDialog?.show(childFragmentManager, LoginFragment::class.java.simpleName)
                }
                is DismissLoading -> {
                    progressDialog?.dismiss()
                }
                is LoginSuccess -> {
                    initLoginSuccess(it.auth)
                }
                is Error -> {
                    showToast(it.throwable.message)
                }
            }
        })
    }

    private fun initLoginSuccess(auth: Auth) {
        val bundle = bundleOf(DashboardFragment.EXTRA_NAME to auth.fullName)
        val navBuilder = NavOptions.Builder()
        val navOptions = navBuilder.setPopUpTo(R.id.dashboardFragment, true).build()
        findNavController().navigate(R.id.dashboardFragment, bundle, navOptions)
    }

    private fun initNavigation() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    activity?.finish()
                    Timber.d("handleOnBackPressed")
                }
            })
    }
}