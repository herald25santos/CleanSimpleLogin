package herald.company.data.repositories

import herald.company.data.mapper.base.Mapper
import herald.company.data.source.local.AuthCache
import herald.company.data.source.remote.AuthRemote
import herald.company.data.source.remote.dto.AuthDto
import herald.company.domain.entities.AuthEntity
import herald.company.domain.repositories.AuthRepository
import io.reactivex.Completable
import io.reactivex.Single

class AuthRepositoryImpl(
    private val remote: AuthRemote,
    private val cache: AuthCache,
    private val mapper: Mapper<AuthEntity, AuthDto>
) : AuthRepository {

    override fun login(data: Map<String, Any>?): Single<AuthEntity> {
        return remote.login(data)
            .map { mapper.map(it) }
            .doOnSuccess { cache.saveUserDetails(it) }
    }

    override fun isLoggedIn(): Single<Boolean> {
        return Single.fromCallable { cache.isLoggedIn() }
    }

    override fun logoutUser(): Completable {
        return Completable.fromAction {
            cache.clearUserDetails()
        }
    }

    override fun getUserDetails(): Single<AuthEntity> {
        return Single.fromCallable { cache.getUserDetails() }
    }

}