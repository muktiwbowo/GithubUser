package m.wb.githubuser.repository

import m.wb.githubuser.data.BaseData
import m.wb.githubuser.service.API
import m.wb.githubuser.service.Status
import javax.inject.Inject

class RepositoryUser @Inject constructor(private val api: API) {
    suspend fun getUsersByUsername(username: String): Status<BaseData> {
        try {
            val response = api.getUsersByUsername(username)
            val body = response.body()
            if (response.isSuccessful) {
                return if (body != null) Status.Success(body)
                else Status.Error(response.message())
            }
            return Status.Error(response.message())
        } catch (e: Exception) {
            return Status.Error(e.localizedMessage ?: e.toString())
        }
    }
}