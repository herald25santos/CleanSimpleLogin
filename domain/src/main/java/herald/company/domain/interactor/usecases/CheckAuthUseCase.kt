package herald.company.domain.interactor.usecases

import io.reactivex.Single


interface CheckAuthUseCase {
    fun executeCheckUserAuth(): Single<Boolean>
}