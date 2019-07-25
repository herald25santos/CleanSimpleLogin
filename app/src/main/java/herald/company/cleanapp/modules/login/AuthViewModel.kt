package herald.company.cleanapp.modules.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import herald.company.cleanapp.common.BaseViewModel
import herald.company.cleanapp.entities.Auth
import herald.company.domain.common.MapperFromCallable
import herald.company.domain.entities.AuthEntity
import herald.company.domain.interactor.usecases.AuthUseCase
import herald.company.domain.interactor.usecases.CheckAuthUseCase
import herald.company.domain.interactor.usecases.GetUserDetailsUseCase
import herald.company.domain.interactor.usecases.LogoutUserUseCase
import io.reactivex.rxkotlin.addTo
import timber.log.Timber

/**
 * Created by herald25santos on 2019-07-08
 */
class AuthViewModel(
    private val authUseCase: AuthUseCase,
    private val checkAuthUseCase: CheckAuthUseCase,
    private val logoutUserUseCase: LogoutUserUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val mapperFromCallable: MapperFromCallable<AuthEntity, Auth>
) : BaseViewModel() {

    private val _authState = MutableLiveData<AuthState>()

    val state: LiveData<AuthState> get() = _authState

    fun login(username: String, password: String) {
        authUseCase.executeLogin(username, password)
            .flatMap { mapperFromCallable.single(it) }
            .doOnSubscribe { _authState.value = Loading }
            .doFinally { _authState.value = DismissLoading }
            .subscribe(
                {
                    _authState.value = LoginSuccess(it)
                }, {
                    Timber.e(it, "login")
                    _authState.value = Error(it)
                }
            ).addTo(compositeDisposable)
    }

    fun isLoggedIn() {
        checkAuthUseCase.executeCheckUserAuth()
            .subscribe(
                {
                    _authState.value = IsLoggedIn(it)
                }, {
                    Timber.e(it, "isLoggedIn")
                    _authState.value = Error(it)
                }
            ).addTo(compositeDisposable)
    }

    fun logoutUser() {
        logoutUserUseCase.executeLogout()
            .subscribe(
                {
                    _authState.value = LogoutSuccess
                }, {
                    Timber.e(it, "logoutUser")
                    _authState.value = Error(it)
                }
            ).addTo(compositeDisposable)
    }

    fun getUserDetails() {
        getUserDetailsUseCase.executeGetUserDetails()
            .flatMap { mapperFromCallable.single(it) }
            .subscribe(
                {
                    _authState.value = UserDetails(it)
                }, {
                    Timber.e(it, "getUserDetails")
                    _authState.value = Error(it)
                }
            ).addTo(compositeDisposable)
    }

}

sealed class AuthState

object Loading : AuthState()

object DismissLoading : AuthState()

object LogoutSuccess : AuthState()

data class IsLoggedIn(val isLoggedIn: Boolean) : AuthState()

data class LoginSuccess(val auth: Auth) : AuthState()

data class UserDetails(val auth: Auth) : AuthState()

data class Error(val throwable: Throwable) : AuthState()