package suitmedia.mobile.core.domain.usecase

import suitmedia.mobile.core.domain.repository.IUserRepository

class UserInteractor(private val userRepository: IUserRepository): UserUseCase  {
    override fun getAllUser(page: Int, per_page: Int) = userRepository.getAllUser(page,per_page)
    override fun getPagedUsers(perPage: Int) = userRepository.getPagedUsers(perPage)
}