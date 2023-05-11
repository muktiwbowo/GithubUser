package m.wb.githubuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import m.wb.githubuser.data.dummyUsers
import m.wb.githubuser.ui.UIContent
import m.wb.githubuser.ui.UIHeader
import m.wb.githubuser.ui.theme.GithubUserTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            /* state of username value */
            val searchValue = remember { mutableStateOf(TextFieldValue("")) }
            val users = remember { mutableStateOf(dummyUsers) }
            GithubUserTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = { UIHeader() },
                        content = {
                            UIContent(
                                searchValue = searchValue,
                                users = users,
                                status = "success"
                            )
                        })
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    val searchValue = remember { mutableStateOf(TextFieldValue("")) }
    val users = remember { mutableStateOf(dummyUsers) }
    GithubUserTheme {
        Scaffold(topBar = {
            UIHeader()
        }) {
            UIContent(
                searchValue = searchValue,
                users = users,
                status = "status"
            )
        }
    }
}