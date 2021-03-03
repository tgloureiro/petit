package br.com.petit

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import br.com.petit.model.Pet
import br.com.petit.model.PetGender

import br.com.petit.ui.theme.PetitTheme
import br.com.petit.ui.theme.Shapes

val pets = listOf(
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
    Pet("Rudy","",PetGender.male,"Description"),
    Pet("Holly","",PetGender.female,"Description"),
    Pet("Maddie","",PetGender.female,"Description"),
    Pet("Roxy","",PetGender.female,"Description"),
    Pet("Zoey","",PetGender.female,"Description"),
    Pet("Duke","",PetGender.male,"Description"),
)

@ExperimentalFoundationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           App()
        }
    }
}

@Composable
@ExperimentalFoundationApi
fun App() {
    val navController = rememberNavController()

    PetitTheme {
        NavHost(navController, startDestination = "main") {
            composable("main") { MainScreen("World", navController) }
            composable("detail") { DetailsScreen(0L, navController) }
            composable("success") { SuccessfulAdoptionScreen(0L, navController) }

        }
    }
}

@Composable
fun AppScaffold(navController: NavController, content: @Composable (PaddingValues) -> Unit) {
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
                                    navController.navigate("detail")
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
                                    navController.navigate("success") {
                                        popUpTo("main",) {
                                            inclusive = false
                                        }
                                    }
                                })
                                .padding(horizontal = 12.dp)
                        )
                    }
                },
                backgroundColor = MaterialTheme.colors.background
            )
        },
        content = content
    )
}

@ExperimentalFoundationApi
@Composable
fun MainScreen(name: String, navController: NavController) {
    val shadowElevationPx = with(LocalDensity.current) { 4.dp.toPx() }
    AppScaffold(navController){

        //fixed: GridCells.Fixed(1)
        //dynamic:
        //Box(contentAlignment = Alignment.BottomCenter) {
        LazyVerticalGrid ( cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(
            horizontal = 8.dp,
            vertical = 24.dp),

            ) {
            //LazyColumn{
                itemsIndexed(items = pets) { index, pet ->
                    Column(modifier = Modifier.padding(horizontal = 8.dp,vertical = 4 .dp)) {

                            Image(
                                painterResource(
                                    when (index%6) {
                                        1 -> {
                                            R.drawable.d1
                                        }
                                        2 -> {
                                            R.drawable.d2
                                        }
                                        3 -> {
                                            R.drawable.d3
                                        }
                                        4 -> {
                                            R.drawable.d4
                                        }
                                        5 -> {
                                            R.drawable.d5
                                        }
                                        6 -> {
                                            R.drawable.d6
                                        }
                                        else -> {
                                            R.drawable.d6
                                        }
                                    }
                                ),
                                "Grid",
                                modifier = Modifier
                                    .aspectRatio(1.305f)
                                    .fillMaxWidth()
                                    .shadow(
                                        8.dp,
                                        shape = RoundedCornerShape(16.dp),
                                        clip = true
                                    ),
                                contentScale = ContentScale.Crop
                            )


                        Text(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 0.dp),
                            text = "${pet.name}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top  = 0.dp,bottom = 16.dp),
                            fontSize = 14.sp,
                            color = Color(0xFF777777),
                            text = "${pet.petGender.name.capitalize()}"
                        )

                    }
                }
            }
            /*Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color(0xDDFFFFFF)
                    )){
                Button(onClick = {},
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxWidth()) {
                    Text(
                        text = "I'm feeling lucky",
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 20.sp

                    )
                }

            }
        }*/
    }
}


@Composable
fun DetailsScreen(petId: Long, navController: NavController) {
    AppScaffold(navController){
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Details screen: Hello Pet!"
        )
    }
}

@Composable
fun SuccessfulAdoptionScreen(petId: Long, navController: NavController) {
    AppScaffold(navController) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Successful Adoption screen: Hello adopted pet!"
        )
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun DefaultPreview() {
    val navController = rememberNavController()

    PetitTheme {
        MainScreen("hello", navController)
    }
}

