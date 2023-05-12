package m.wb.githubuser.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import m.wb.githubuser.data.BaseData
import m.wb.githubuser.repository.RepositoryUser
import m.wb.githubuser.service.Status
import javax.inject.Inject

@HiltViewModel
class ViewModelUser @Inject constructor(
    private val repoUser: RepositoryUser
) : ViewModel() {
    val getUsersByUsername = MutableLiveData<Status<List<BaseData.DataUser>>>()
    fun getUsersByUsername(username: String) {
        viewModelScope.launch {
            getUsersByUsername.value = Status.Loading()
            val response = repoUser.getUsersByUsername(username)
            withContext(Dispatchers.Main) {
                if (response is Status.Success) {
                    getUsersByUsername.value = Status.Success(data = response.data?.items)
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