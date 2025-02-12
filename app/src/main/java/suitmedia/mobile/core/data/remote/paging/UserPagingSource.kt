package suitmedia.mobile.core.data.remote.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.flow.first
import suitmedia.mobile.core.data.remote.RemoteDataSource
import suitmedia.mobile.core.data.remote.network.ApiResponse
import suitmedia.mobile.core.data.remote.response.UserResponse

class UserPagingSource(
    private val remoteDataSource: RemoteDataSource
) : PagingSource<Int, UserResponse>() {
    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        } ?: 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        val page = params.key ?: 1
        return try {
            val response = remoteDataSource.getAllUser(page, params.loadSize).first()
            when (response) {
                is ApiResponse.Success -> {
                    Log.d("PagingDebug", "Page: $page, Requested: ${params.loadSize}, Loaded: ${response.data.size}")
                    LoadResult.Page(
                        data = response.data,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (response.data.isEmpty()) null else page + 1
                    )
                }
                is ApiResponse.Empty -> LoadResult.Page(emptyList(), null, null)
                is ApiResponse.Error -> LoadResult.Error(Exception(response.errorMessage))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}