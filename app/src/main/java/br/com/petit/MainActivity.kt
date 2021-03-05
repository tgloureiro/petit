package br.com.petit

import android.os.Bundle
import android.system.Os.close
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
    Pet("Rudy", "", PetGender.male, "Description"),
    Pet("Holly", "", PetGender.female, "Description"),
    Pet("Maddie", "", PetGender.female, "Description"),
    Pet("Roxy", "", PetGender.female, "Description"),
    Pet("Zoey", "", PetGender.female, "Description"),
    Pet("Duke", "", PetGender.male, "Description"),
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

val ListDensityLocal = compositionLocalOf { ListDensity.TWO }


@Composable
fun AppScaffold(
    navController: NavController,
    listDensity: ListDensity,
    onListDensityChanged: (ListDensity) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
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

                        Crossfade(targetState = listDensity) {
                            when (it) {
                                ListDensity.ONE -> {
                                    Icon(
                                        painterResource(id = R.drawable.ic_selected_grid),
                                        "Grid",
                                        tint = Color(0xFFB2B2B2),
                                        modifier = Modifier
                                            .padding(horizontal = 12.dp)
                                            .clickable {
                                                onListDensityChanged(ListDensity.TWO)
                                            }
                                    )


                                }
                                ListDensity.TWO -> {
                                    Image(
                                        painterResource(id = R.drawable.ic_selected_grid),
                                        "Grid",
                                        Modifier
                                            .padding(horizontal = 12.dp)
                                            .clickable {
                                                onListDensityChanged(ListDensity.TWO)
                                            }
                                    )
                                }
                            }
                        }

                        Crossfade(targetState = listDensity) {
                            when (it) {
                                ListDensity.ONE -> {
                                    Image(
                                        painterResource(id = R.drawable.ic_selected_list),
                                        "List",
                                        Modifier
                                            .clickable(onClick = {
                                                onListDensityChanged(ListDensity.ONE)
                                            })
                                            .padding(horizontal = 12.dp)
                                    )
                                }
                                ListDensity.TWO -> {
                                    Icon(
                                        painterResource(id = R.drawable.ic_selected_list),
                                        "List",
                                        tint = Color(0xFFB2B2B2),
                                        modifier = Modifier
                                            .clickable(onClick = {
                                                onListDensityChanged(ListDensity.ONE)
                                            })
                                            .padding(horizontal = 12.dp)
                                    )
                                }

                            }
                        }




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
                                        popUpTo("main") {
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


enum class ListDensity {
    ONE,
    TWO
}


@ExperimentalFoundationApi
@Composable
fun MainScreen(name: String, navController: NavController) {

    var densitySelector by rememberSaveable { mutableStateOf(ListDensity.TWO) }

    AppScaffold(navController, densitySelector, { listDensity -> densitySelector = listDensity }) {

        //fixed: GridCells.Fixed(1)
        //dynamic:
        Box(contentAlignment = Alignment.BottomCenter) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(
                when (densitySelector) {
                    ListDensity.ONE -> 1
                    ListDensity.TWO -> 2
                }
            ),
            contentPadding = PaddingValues(
                horizontal = 8.dp,
                vertical = 24.dp
            ),

            ) {
            //LazyColumn{
            itemsIndexed(items = pets) { index, pet ->
                Column(modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clickable {
                        navController.navigate("detail")
                    }) {

                    Image(
                        painterResource(
                            when (index % 6) {
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
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 4.dp,
                            bottom = 0.dp
                        ),
                        text = "${pet.name}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 0.dp,
                            bottom = 16.dp
                        ),
                        fontSize = 14.sp,
                        color = Color(0xFF777777),
                        text = "${pet.petGender.name.capitalize()}"
                    )

                }
            }
        }
        Box(contentAlignment = Alignment.Center,
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
    }
    }
}


@Composable
fun DetailsScreen(petId: Long, navController: NavController) {
    Scaffold(topBar = {
        PetitTopAppBar({navController.popBackStack()})

    }) {
        Box (contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxHeight()) {
            Column(modifier =
            Modifier.fillMaxHeight().verticalScroll(rememberScrollState())) {
                //img/
                Image(
                    painterResource(
                        R.drawable.d5
                    ),
                    "Grid",
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .aspectRatio(1.305f)
                        .shadow(
                            8.dp,
                            shape = RoundedCornerShape(16.dp),
                            clip = true
                        ),
                    contentScale = ContentScale.Crop
                )
                //title
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 0.dp,
                        bottom = 0.dp
                    ),
                    text = "Zoey",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 0.dp,
                        bottom = 16.dp
                    ),
                    fontSize = 16.sp,
                    color = Color(0xFF777777),
                    text = "Female"
                )
                //description
                Text(
                    modifier = Modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 0.dp,
                        bottom = 16.dp
                    ),
                    fontSize = 16.sp,
                    color = Color(0xFF777777),
                    text = "Zoey loves a run, so if you’re looking for a jogging companion or someone to take long walks with, then Zoey’s your lady. She does need a secure yard with high fences, but her long legs make Zoey a very good looking girl. Everyone comments on how pretty she is – she’s a real head turner. She’s also very loyal and will want to spend time with her family, so someone home for part of the day would suit her best. Be Zoey’s hero!"
                )



            }
            //footer button
            Button(
                onClick = {
                          navController.navigate("success"){
                              popUpTo("main"){ inclusive = false }
                          }
                },
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Adopt Zoey",
                    modifier = Modifier.padding(vertical = 8.dp),
                    fontSize = 20.sp

                )
            }
        }
    }
}

@Composable
private fun PetitTopAppBar(
    onBackPressed: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                IconButton(
                    onClick = onBackPressed
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.app_name)
                    )
                }
            }
        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(horizontal = 24.dp),
            text = "About her",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )
    }
}


@Composable
fun SuccessfulAdoptionScreen(petId: Long, navController: NavController) {
    Scaffold( topBar = {
        TopAppBar(
            title = {
                Image(
                    painter = painterResource(R.drawable.ic_logo_app_bar),
                    contentDescription = "menu",
                )
            },
            backgroundColor = MaterialTheme.colors.background

        )}) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())) {
                    //title
                    Text(
                        modifier = Modifier.padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp,
                            bottom = 16.dp
                        ),
                        text = "Congratulations! You adopted Zoey",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    //img/
                    Image(
                        painterResource(
                            R.drawable.d5
                        ),
                        "Grid",
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .aspectRatio(1.305f)
                            .widthIn(max=128.dp)
                            .shadow(
                                8.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = true
                            ),
                        contentScale = ContentScale.Crop
                    )

                    Image(
                        painterResource(
                            R.drawable.map
                        ),
                        "Map",
                        modifier = Modifier
                            .padding(horizontal = 46.dp, vertical = 24.dp)
                            .aspectRatio(1.69f)
                            .fillMaxWidth()
                            .shadow(
                                8.dp,
                                shape = RoundedCornerShape(16.dp),
                                clip = true
                            ),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = "Your new buddy is being comfortably delivered to your house right now. ",
                        modifier = Modifier.padding(horizontal = 36.dp),
                        textAlign = TextAlign.Center
                    )
                    //footer button
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .padding(horizontal = 24.dp, vertical = 36.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.background)

                    ) {
                        Text(
                            text = "CANCEL",
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 46.dp),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }



    }
}



@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun DefaultPreview() {
    val navController = rememberNavController()

    PetitTheme {
        SuccessfulAdoptionScreen(0L, navController)
    }
}

