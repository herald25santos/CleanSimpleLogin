package herald.company.data.source.local

import herald.company.domain.entities.AuthEntity

interface AuthCache {

    fun saveUserDetails(auth: AuthEntity)

    fun isLoggedIn(): Boolean

    fun clearUserDetails()

    fun getUserDetails(): AuthEntity

}