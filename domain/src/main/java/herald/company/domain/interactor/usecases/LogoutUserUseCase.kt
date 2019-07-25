package herald.company.domain.interactor.usecases

import io.reactivex.Completable


interface LogoutUserUseCase {
    fun executeLogout(): Completable
}