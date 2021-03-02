package br.com.petit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.animatedVectorResource
import androidx.compose.ui.res.painterResource
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
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(R.drawable.ic_logo_app_bar),
                        contentDescription = "menu",
                    )
                },
                actions = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painterResource(id = R.drawable.ic_selected_grid),
                            "Grid",
                            Modifier.padding(horizontal = 12.dp)
                        )

                        Icon(
                            painterResource(id = R.drawable.ic_selected_list),
                            "List",
                            tint = Color(0xFFB2B2B2),
                            modifier = Modifier
                                .clickable(onClick = {
                                    Toast
                                        .makeText(context, "List", Toast.LENGTH_SHORT)
                                        .show()
                                })
                                .padding(horizontal = 12.dp)
                        )

                        Icon(
                            Icons.Filled.Search,
                            "Search",
                            tint = Color(0xFFB2B2B2),
                            modifier = Modifier
                                .clickable(onClick = {
                                    Toast
                                        .makeText(context, "Search", Toast.LENGTH_SHORT)
                                        .show()
                                })
                                .padding(horizontal = 12.dp)
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.background
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

