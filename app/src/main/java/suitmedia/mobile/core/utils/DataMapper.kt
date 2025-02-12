package suitmedia.mobile.core.utils

import suitmedia.mobile.core.data.remote.response.UserResponse
import suitmedia.mobile.core.domain.model.User

object DataMapper {
    fun UserResponse.toDomain(): User {
        return User(
            id = this.id,
            email = this.email,
            first_name = this.first_name,
            last_name = this.last_name,
            avatar = this.avatar
        )
    }

    fun List<UserResponse>.toDomainList(): List<User> {
        return this.map { it.toDomain() }
    }
}