package herald.company.domain.interactor.usecases

import herald.company.domain.entities.AuthEntity
import io.reactivex.Single


interface GetUserDetailsUseCase {
    fun executeGetUserDetails(): Single<AuthEntity>
}