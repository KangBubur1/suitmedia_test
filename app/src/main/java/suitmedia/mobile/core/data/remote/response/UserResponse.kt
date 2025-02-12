package suitmedia.mobile.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("first_name")
    val first_name: String,

    @field:SerializedName("last_name")
    val last_name: String,

    @field:SerializedName("avatar")
    val avatar: String,
)