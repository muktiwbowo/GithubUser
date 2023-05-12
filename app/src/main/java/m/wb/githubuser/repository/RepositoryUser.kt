package m.wb.githubuser.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import m.wb.githubuser.data.BaseData
import m.wb.githubuser.service.API
import m.wb.githubuser.service.Status
import okhttp3.ResponseBody
import javax.inject.Inject

class RepositoryUser @Inject constructor(private val api: API) {
    suspend fun getUsersByUsername(username: String): Status<BaseData> {
        try {
            val response = api.getUsersByUsername(username)
            val body = response.body()
            val error = convertErrorBody(response.errorBody())
            if (response.isSuccessful) {
                return if (body != null) Status.Success(body)
                else Status.Error(error?.message ?: "")
            }
            return Status.Error(error?.message ?: "")
        } catch (e: Exception) {
            return Status.Error(e.localizedMessage ?: e.toString())
        }
    }

    private fun convertErrorBody(errorResponse: ResponseBody?): BaseData? {
        return try {
            val gson = Gson()
            val type = object : TypeToken<BaseData>() {}.type
            gson.fromJson(errorResponse?.charStream(), type)
        } catch (exception: Exception) {
            null
        }
    }
}