package br.com.petit.ui.screen

import MainAppBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import br.com.petit.R
import br.com.petit.ui.theme.PetitTheme
import br.com.petit.ui.util.ListDensity
import br.com.petit.viewmodel.MainScreenViewModel
import dev.chrisbanes.accompanist.coil.CoilImage

@ExperimentalFoundationApi
@Composable
fun MainScreen(viewModel: MainScreenViewModel) {
    var densitySelector by rememberSaveable { mutableStateOf(ListDensity.TWO) }

    val pets by viewModel.pets.collectAsState(listOf())

    Scaffold(topBar = {
        MainAppBar(
            listDensity = densitySelector,
            onListDensityClick = {
                listDensity ->
                densitySelector = listDensity
            },
            onSearchClick = {

            }
        )
    }) {
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
                            viewModel.onPetSelected(pet.id)
                        }) {

                        CoilImage(
                            data = pet.pictureUrl,
                            "Grid",
                            modifier = Modifier
                                .aspectRatio(1.305f)
                                .fillMaxWidth()
                                .clip(
                                    shape = RoundedCornerShape(16.dp),
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
                            text = pet.name,
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
                            text = pet.petGender.name.capitalize()
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
                Button(onClick = {
                    //   viewModel.deleteFirst()
                },
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

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun MainScreenPreview() {
    val viewModel : MainScreenViewModel = viewModel()

    PetitTheme() {
        MainScreen(viewModel)
    }
}