package suitmedia.mobile.core.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flow
import suitmedia.mobile.core.data.remote.network.ApiResponse
import suitmedia.mobile.core.data.remote.paging.UserPagingSource
import suitmedia.mobile.core.domain.model.User
import suitmedia.mobile.core.domain.repository.IUserRepository
import suitmedia.mobile.core.utils.AppExecutors

import suitmedia.mobile.core.utils.DataMapper.toDomain
import suitmedia.mobile.di.viewModelModule

class UserRepository(
    val remoteDataSource: RemoteDataSource,
    val appExecutors: AppExecutors
) : IUserRepository{


    override fun getAllUser(page: Int, per_page: Int): Flow<Resource<List<User>>> = flow {
        emit(Resource.Loading())
        try {
            val response = remoteDataSource.getAllUser(page, per_page).first()
            when (response) {
                is ApiResponse.Success -> {
                    val data = response.data.map { it.toDomain() }
                    emit(Resource.Success(data))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Success(listOf()))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(response.errorMessage))
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    override fun getPagedUsers(per_page: Int): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = per_page,
                initialLoadSize = 10,
                prefetchDistance = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UserPagingSource(remoteDataSource) }
        ).flow.map { pagingData ->
            pagingData.map { userResponse ->
                userResponse.toDomain()
            }
        }
    }

}