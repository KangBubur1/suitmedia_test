package suitmedia.mobile.core.data.remote.network

import retrofit2.http.GET
import retrofit2.http.Query
import suitmedia.mobile.core.data.remote.response.ListUserResponse

interface ApiService {
    @GET("api/users")
    suspend fun getUser(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): ListUserResponse
}