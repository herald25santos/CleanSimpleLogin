package herald.company.data.source.remote

import herald.company.data.source.remote.dto.AuthDto
import io.reactivex.Single

interface AuthRemote {

    fun login(data: Map<String, Any>?): Single<AuthDto>

}