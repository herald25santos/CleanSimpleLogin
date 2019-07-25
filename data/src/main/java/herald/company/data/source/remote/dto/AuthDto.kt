package herald.company.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("username")
    var username: String? = null,
    @SerializedName("password")
    var password: String? = null,
    @SerializedName("full_name")
    var fullName: String? = null
)