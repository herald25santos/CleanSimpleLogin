package herald.company.domain.interactor.usecases.impl

import herald.company.domain.common.BaseCompletableUseCase
import herald.company.domain.common.BaseSingleUseCase
import herald.company.domain.common.CompletableRxTransformer
import herald.company.domain.common.SingleRxTransformer
import herald.company.domain.interactor.usecases.CheckAuthUseCase
import herald.company.domain.interactor.usecases.LogoutUserUseCase
import herald.company.domain.repositories.AuthRepository
import io.reactivex.Completable
import io.reactivex.Single

/**
 * It will first get articles from the local database and also update it with the latest
 * articles from remote
 */
class LogoutUserUseCaseImpl(
    transformer: CompletableRxTransformer,
    private val repository: AuthRepository
) : BaseCompletableUseCase(transformer), LogoutUserUseCase {

    override fun executeLogout(): Completable {
        return completable()
    }

    override fun createCompletable(data: Map<String, Any>?): Completable {
        return repository.logoutUser()
    }

}