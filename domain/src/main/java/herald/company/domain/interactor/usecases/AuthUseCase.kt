package herald.company.domain.interactor.usecases

import herald.company.domain.entities.AuthEntity
import io.reactivex.Single


interface AuthUseCase {
    fun executeLogin(username: String, password: String): Single<AuthEntity>
}