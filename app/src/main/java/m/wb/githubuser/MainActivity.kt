package m.wb.githubuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
            Text(text = searchValue)
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