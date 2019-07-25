package herald.company.domain.interactor.usecases.impl

import herald.company.domain.common.BaseSingleUseCase
import herald.company.domain.common.SingleRxTransformer
import herald.company.domain.interactor.usecases.CheckAuthUseCase
import herald.company.domain.repositories.AuthRepository
import io.reactivex.Single

/**
 * It will first get articles from the local database and also update it with the latest
 * articles from remote
 */
class CheckAuthUseCaseImpl(
    transformer: SingleRxTransformer<Boolean>,
    private val repository: AuthRepository
) : BaseSingleUseCase<Boolean>(transformer), CheckAuthUseCase {

    override fun executeCheckUserAuth(): Single<Boolean> {
        return single()
    }


    override fun createSingle(data: Map<String, Any>?): Single<Boolean> {
        return repository.isLoggedIn()
    }

}