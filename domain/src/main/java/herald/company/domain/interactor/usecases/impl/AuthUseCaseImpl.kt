package herald.company.domain.interactor.usecases.impl

import herald.company.domain.common.BaseSingleUseCase
import herald.company.domain.common.SingleRxTransformer
import herald.company.domain.interactor.usecases.AuthUseCase
import herald.company.domain.entities.AuthEntity
import herald.company.domain.repositories.AuthRepository
import io.reactivex.Single

/**
 * It will first get articles from the local database and also update it with the latest
 * articles from remote
 */
class AuthUseCaseImpl(
    transformer: SingleRxTransformer<AuthEntity>,
    private val repository: AuthRepository
) : BaseSingleUseCase<AuthEntity>(transformer), AuthUseCase {

    override fun executeLogin(username: String, password: String): Single<AuthEntity> {
        val data = HashMap<String, String>()
        data["username"] = username
        data["password"] = password
        return single(data)
    }

    override fun createSingle(data: Map<String, Any>?): Single<AuthEntity> {
        return repository.login(data)
    }

}