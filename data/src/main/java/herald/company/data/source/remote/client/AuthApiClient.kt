package herald.company.data.source.remote.client

import herald.company.data.source.remote.dto.AuthDto
import io.reactivex.Single
import retrofit2.http.GET

interface AuthApiClient {

    @GET("login")
    fun login(data: Map<String, Any>?): Single<AuthDto>

}