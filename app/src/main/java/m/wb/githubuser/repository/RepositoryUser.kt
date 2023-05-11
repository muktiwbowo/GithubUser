package m.wb.githubuser.repository

import m.wb.githubuser.data.DataUser
import m.wb.githubuser.service.API
import m.wb.githubuser.service.Status

class RepositoryUser(private val api: API) {
    suspend fun getUsersByUsername(username: String): Status<DataUser> {
        try {
            val response = api.getUsersByUsername(username, 20)
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