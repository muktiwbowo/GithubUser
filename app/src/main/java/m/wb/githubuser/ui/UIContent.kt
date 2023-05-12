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
import androidx.compose.runtime.MutableState
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
import m.wb.githubuser.R
import m.wb.githubuser.data.BaseData

@Composable
fun UIContent(
    searchValue: MutableState<TextFieldValue>,
    users: MutableState<List<BaseData.DataUser>>,
    status: String
) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
        UISearch(searchState = searchValue, users = users)
        if (status.contains("loading")) UIStatus(message = "Loading...")
        else if (status.contains("success")) UIUser(users = users)
        else {
            UIStatus(message = "Not Found")
        }
    }
}

@Composable
fun UISearch(
    searchState: MutableState<TextFieldValue>,
    users: MutableState<List<BaseData.DataUser>>
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = searchState.value,
        onValueChange = { result ->
            searchState.value = result
        },
        placeholder = { Text(text = "Search...") },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                /* NOTE: call api based on search value */
                focusManager.clearFocus()
            }
        )
    )
}

@Composable
fun UIUser(users: MutableState<List<BaseData.DataUser>>) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)) {
        items(users.value) { user ->
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
                    painter = painterResource(id = R.drawable.ic_launcher_background),
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