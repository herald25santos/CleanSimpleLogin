package herald.company.domain.repositories

import herald.company.domain.entities.AuthEntity
import io.reactivex.Completable
import io.reactivex.Single

interface AuthRepository {

    fun login(data: Map<String, Any>?): Single<AuthEntity>

    fun isLoggedIn(): Single<Boolean>

    fun logoutUser(): Completable

    fun getUserDetails(): Single<AuthEntity>

}