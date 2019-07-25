package herald.company.data.source.local.impl

import herald.company.data.common.extensions.nullable
import herald.company.data.source.local.AuthCache
import herald.company.data.source.local.RxSharedPreferencesUtil
import herald.company.domain.entities.AuthEntity

class AuthCacheImpl constructor(
    private val rxSharedPreferencesUtil: RxSharedPreferencesUtil
) : AuthCache {

    override fun saveUserDetails(auth: AuthEntity) {
        rxSharedPreferencesUtil.isLoggedIn().set(true)
        rxSharedPreferencesUtil.fullName().set(auth.fullName.nullable())
    }

    override fun isLoggedIn(): Boolean {
        return rxSharedPreferencesUtil.isLoggedIn().get()
    }

    override fun clearUserDetails() {
        rxSharedPreferencesUtil.isLoggedIn().set(false)
        rxSharedPreferencesUtil.fullName().delete()
    }

    override fun getUserDetails(): AuthEntity {
        return AuthEntity(fullName = rxSharedPreferencesUtil.fullName().get())
    }

}