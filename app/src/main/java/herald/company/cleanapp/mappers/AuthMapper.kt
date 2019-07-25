package herald.company.cleanapp.mappers

import herald.company.cleanapp.entities.Auth
import herald.company.domain.common.MapperFromCallable
import herald.company.domain.entities.AuthEntity

class AuthMapper : MapperFromCallable<AuthEntity, Auth>() {

    override fun mapFrom(from: AuthEntity): Auth {
        return map(from)
    }

    private fun map(from: AuthEntity): Auth =
        Auth(
            id = from.id,
            fullName = from.fullName
        )

}