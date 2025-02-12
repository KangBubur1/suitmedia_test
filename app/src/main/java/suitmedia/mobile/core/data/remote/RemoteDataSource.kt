package suitmedia.mobile.core.data.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import suitmedia.mobile.core.data.remote.network.ApiResponse
import suitmedia.mobile.core.data.remote.network.ApiService
import suitmedia.mobile.core.data.remote.response.UserResponse

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllUser(page: Int, per_page:Int): Flow<ApiResponse<List<UserResponse>>> {
        return flow{
            try{
                val response = apiService.getUser(page,per_page)
                val dataArray = response.data
                if(dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.data))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}