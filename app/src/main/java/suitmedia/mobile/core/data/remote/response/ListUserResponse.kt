package suitmedia.mobile.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class ListUserResponse(
    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("per_page")
    val per_page: Int,

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("total_pages")
    val total_pages: Int,

    @field:SerializedName("data")
    val data: List<UserResponse>,



    )