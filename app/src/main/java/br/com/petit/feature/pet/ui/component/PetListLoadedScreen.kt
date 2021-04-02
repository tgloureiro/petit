package br.com.petit.feature.pet.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.petit.R
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.core.ui.util.ListDensity
import br.com.petit.feature.adoption.bloc.AdoptionState
import br.com.petit.feature.adoption.bloc.ValidAdoption
import br.com.petit.feature.adoption.model.Adoption
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.*

@ExperimentalFoundationApi
@Composable
fun PetListLoadedScreen(
    adoptionState: AdoptionState,
    pets: List<Pet>,
    densitySelector: ListDensity,
    onListDensityClick: (ListDensity) -> Unit,
    navigateToPetDetails: (Long) -> Unit,
    onFeelingLucky: (pets: List<Pet>) -> Unit
) {
  Scaffold(
      topBar = {
        MainAppBar(listDensity = densitySelector, onListDensityClick = onListDensityClick)
      },
      bottomBar = {
        if (pets.isNotEmpty()) {
          Box(
              contentAlignment = Alignment.Center,
              modifier = Modifier.fillMaxWidth().background(color = Color(0xDDFFFFFF))) {
            Button(
                onClick = { onFeelingLucky(pets) },
                modifier = Modifier.padding(24.dp).fillMaxWidth()) {
              Text(
                  text = stringResource(id = R.string.feeling_lucky_button_text),
                  modifier = Modifier.padding(vertical = 8.dp),
                  fontSize = 20.sp)
            }
          }
        }
      }) {
    LazyVerticalGrid(
        cells =
            GridCells.Fixed(
                when (densitySelector) {
                  ListDensity.ONE -> 1
                  ListDensity.TWO -> 2
                }),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 24.dp),
        modifier = Modifier.padding(bottom = 64.dp)) {
      // LazyColumn{
      itemsIndexed(items = pets) { _, pet ->
        when (densitySelector) {
          ListDensity.ONE -> {
            Row(
                modifier =
                    Modifier.height(164.dp)
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                        .clickable { navigateToPetDetails(pet.id) }) {
              Box {
                CoilImage(
                    data = pet.pictureUrl,
                    "Grid",
                    modifier =
                        Modifier.aspectRatio(1.305f)
                            .fillMaxWidth()
                            .clip(
                                shape = RoundedCornerShape(16.dp),
                            ),
                    contentScale = ContentScale.Crop) {
                  Box(Modifier.matchParentSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                  }
                }

                if (adoptionState is ValidAdoption && adoptionState.adoption.pet.id == pet.id) {
                    Row(modifier = Modifier.background(Color.White, RoundedCornerShape(0.dp,0.dp,8.dp,0.dp)).padding(8.dp)) {
                        Icon(
                            Icons.Filled.Star,
                            tint = MaterialTheme.colors.primary,
                            modifier = Modifier.width(16.dp),
                            contentDescription = "Adoption")
                    }
                }
              }
              Column{
                Text(
                    modifier =
                        Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 0.dp),
                    text = pet.name,
                    maxLines = 1,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold)
                Text(
                    modifier =
                        Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp),
                    fontSize = 18.sp,
                    maxLines = 1,
                    color = Color(0xFF777777),
                    text = pet.petGender.name.capitalize(Locale.getDefault()))
              }
            }
          }
          ListDensity.TWO -> {
            Column(
                modifier =
                    Modifier.padding(horizontal = 24.dp, vertical = 4.dp).width(128.dp).clickable {
                      navigateToPetDetails(pet.id)
                    }) {
              Box {
                CoilImage(
                    data = pet.pictureUrl,
                    "Grid",
                    modifier =
                        Modifier.aspectRatio(1.305f)
                            .fillMaxWidth()
                            .clip(
                                shape = RoundedCornerShape(16.dp),
                            ),
                    contentScale = ContentScale.Crop) {
                  Box(Modifier.matchParentSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                  }
                }
                if (adoptionState is ValidAdoption && adoptionState.adoption.pet.id == pet.id) {
                  Row(modifier = Modifier.background(Color.White, RoundedCornerShape(0.dp,0.dp,8.dp,0.dp)).padding(8.dp)) {
                      Icon(
                          Icons.Filled.Star,
                          tint = MaterialTheme.colors.primary,
                          modifier = Modifier.width(16.dp),
                          contentDescription = "Adoption")
                  }
                }
              }

              Text(
                  modifier =
                      Modifier.padding(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 0.dp),
                  text = pet.name,
                  fontSize = 18.sp,
                  fontWeight = FontWeight.Bold)
              Text(
                  modifier =
                      Modifier.padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp),
                  fontSize = 14.sp,
                  color = Color(0xFF777777),
                  text = pet.petGender.name.capitalize(Locale.getDefault()))
            }
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun PetListLoadedScreenPreview() {
  val pet =
      Pet(
          0,
          "Rudy",
          "https://images.dog.ceo/breeds/spaniel-welsh/n02102177_3639.jpg",
          PetGender.MALE,
          "Description")

  PetitTheme{
    PetListLoadedScreen(
        ValidAdoption(Adoption(0, pet)),
        listOf(pet, pet, pet, pet, pet),
        ListDensity.TWO,
        {},
        {},
        {})
  }
}
