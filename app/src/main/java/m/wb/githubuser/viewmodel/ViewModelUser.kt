package m.wb.githubuser.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import m.wb.githubuser.data.DataUser
import m.wb.githubuser.repository.RepositoryUser
import m.wb.githubuser.service.Status

class ViewModelUser(
    private val repoUser: RepositoryUser
) : ViewModel() {
    val getUsersByUsername = MutableLiveData<Status<DataUser>>()
    fun getUsersByUsername(username: String) {
        viewModelScope.launch {
            getUsersByUsername.value = Status.Loading()
            val response = repoUser.getUsersByUsername(username)
            withContext(Dispatchers.Main) {
                if (response is Status.Success) {
                    getUsersByUsername.value = Status.Success(data = response.data)
                } else if (response is Status.Error) {
                    getUsersByUsername.value =
                        Status.Error(
                            message = response.message
                                ?: "Something went wrong, contact administrator"
                        )
                }
            }
        }
    }
}