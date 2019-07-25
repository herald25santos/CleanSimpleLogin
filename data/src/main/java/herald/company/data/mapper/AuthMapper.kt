package herald.company.data.mapper

import herald.company.data.mapper.base.Mapper
import herald.company.data.source.remote.dto.AuthDto
import herald.company.domain.entities.AuthEntity

class AuthMapper : Mapper<AuthEntity, AuthDto>() {

    override fun map(from: AuthDto): AuthEntity {
        return AuthEntity(
            from.id,
            from.fullName
        )
    }

    override fun reverse(to: AuthEntity): AuthDto {
        return AuthDto(
            to.id,
            to.fullName
        )
    }

}