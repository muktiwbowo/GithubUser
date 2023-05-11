package m.wb.githubuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import m.wb.githubuser.ui.theme.GithubUserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUserTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PageHome()
                }
            }
        }
    }
}

@Composable
fun PageHome() {
    var searchValue by remember { mutableStateOf("") }

    /** show edit text and list of users here **/
    Scaffold(topBar = {
        TopAppBar {
            Text(
                text = "Github User",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 18.dp)
            )
        }
    }) {
        /** show edit text and list of users here **/
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)) {
            /** text field for typing github username **/
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchValue,
                onValueChange = {
                    searchValue = it
                },
                placeholder = { Text(text = "Search...") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        /* call api based on search value */
                    }
                )
            )
            /** list of users **/
            LazyColumn(contentPadding = PaddingValues(horizontal = 4.dp, vertical = 8.dp)) {
                items(5) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        /** avatar **/
                        Image(
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(48.dp),
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "avatar",
                        )
                        Column(
                            modifier = Modifier.padding(horizontal = 12.dp)
                        ) {
                            /** full name **/
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 2.dp),
                                text = "Full Name",
                                color = Color.Black,
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                            /** username **/
                            Text(
                                modifier = Modifier.padding(bottom = 2.dp),
                                text = "@username",
                                color = Color.Gray,
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    GithubUserTheme {
        PageHome()
    }
}