package suitmedia.mobile.core.domain.repository

import androidx.paging.PagingData

import kotlinx.coroutines.flow.Flow
import suitmedia.mobile.core.data.remote.Resource
import suitmedia.mobile.core.domain.model.User

interface IUserRepository {
    fun getAllUser(page: Int, per_page:Int): Flow<Resource<List<User>>>
    fun getPagedUsers(per_page: Int): Flow<PagingData<User>>
}