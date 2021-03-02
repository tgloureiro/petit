package br.com.petit

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.petit.ui.theme.PetitTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppScaffold()
        }
    }
}


@Composable
fun AppScaffold() {
    val white = Color(0xFFFFFFFF)
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Petit") },
                backgroundColor = white
            )
        },
    ) {
        MainScreen("World")
    }
}

@Composable
fun MainScreen(name: String) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = "Main screen: Hello $name!"
    )
}


@Composable
fun DetailsScreen(name: String) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = "Details screen: Hello $name!"
    )
}

@Composable
fun SuccessfulAdoptionScreen(name: String) {
    Text(modifier = Modifier.padding(16.dp), text = "Successful Adoption screen: Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PetitTheme {
        AppScaffold()
    }
}

