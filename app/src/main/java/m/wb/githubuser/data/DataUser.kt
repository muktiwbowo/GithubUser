package m.wb.githubuser.data

import com.google.gson.annotations.SerializedName

data class BaseData(
    @SerializedName("total_count")
    val totalCount: Long,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<DataUser>,
    @SerializedName("message")
    val message: String

) {
    data class DataUser(
        @SerializedName("login")
        val name: String,
        @SerializedName("avatar_url")
        val avatar: String
    )
}