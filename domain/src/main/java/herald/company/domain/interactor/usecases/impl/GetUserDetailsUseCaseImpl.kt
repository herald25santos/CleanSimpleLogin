package herald.company.domain.interactor.usecases.impl

import herald.company.domain.common.BaseSingleUseCase
import herald.company.domain.common.SingleRxTransformer
import herald.company.domain.interactor.usecases.AuthUseCase
import herald.company.domain.entities.AuthEntity
import herald.company.domain.interactor.usecases.GetUserDetailsUseCase
import herald.company.domain.repositories.AuthRepository
import io.reactivex.Single

/**
 * It will first get articles from the local database and also update it with the latest
 * articles from remote
 */
class GetUserDetailsUseCaseImpl(
    transformer: SingleRxTransformer<AuthEntity>,
    private val repository: AuthRepository
) : BaseSingleUseCase<AuthEntity>(transformer), GetUserDetailsUseCase {

    override fun executeGetUserDetails(): Single<AuthEntity> {
        return single()
    }

    override fun createSingle(data: Map<String, Any>?): Single<AuthEntity> {
        return repository.getUserDetails()
    }

}