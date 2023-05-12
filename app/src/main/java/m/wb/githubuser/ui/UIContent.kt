package m.wb.githubuser.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import m.wb.githubuser.R
import m.wb.githubuser.data.BaseData
import m.wb.githubuser.service.Status
import m.wb.githubuser.viewmodel.ViewModelUser

@Composable
fun UIContent(
    viewModelUser: ViewModelUser = hiltViewModel()
) {
    val state = viewModelUser.getUsersByUsername.observeAsState()
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
        UISearch()

        when (state.value) {
            is Status.Loading -> {
                UIStatus(message = "Loading...")
            }
            is Status.Success -> {
                val values = state.value?.data
                if (!values.isNullOrEmpty()) UIUser(users = values)
                else UIStatus(message = "Not found")
            }
            is Status.Error -> {
                UIStatus(message = "Something is wrong with github api")
            }
        }
    }
}

@Composable
fun UISearch(
    viewModelUser: ViewModelUser = hiltViewModel()
) {
    val searchValue = remember { mutableStateOf(TextFieldValue("")) }
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchValue.value,
        onValueChange = { result ->
            searchValue.value = result
        },
        placeholder = { Text(text = "Search...") },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                /* call api based on search value */
                viewModelUser.getUsersByUsername(searchValue.value.text)
                focusManager.clearFocus()
            }
        )
    )
}

@Composable
fun UIUser(users: List<BaseData.DataUser>) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)) {
        items(users) { user ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                /* avatar */
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp),
                    painter = rememberAsyncImagePainter(user.avatar),
                    contentDescription = "avatar",
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    text = user.name,
                    color = Color.Black,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun UIStatus(message: String) {
    Text(
        text = message,
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center),
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = Color.Black
    )
}