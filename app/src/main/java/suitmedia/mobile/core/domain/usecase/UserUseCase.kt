package suitmedia.mobile.core.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import suitmedia.mobile.core.data.remote.Resource
import suitmedia.mobile.core.domain.model.User

interface UserUseCase {
    fun getAllUser(page: Int, per_page:Int): Flow<Resource<List<User>>>
    fun getPagedUsers(perPage: Int): Flow<PagingData<User>>
}