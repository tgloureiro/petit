package br.com.petit.feature.pet.ui.component

import MainAppBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.petit.R
import br.com.petit.core.ui.util.ListDensity
import br.com.petit.feature.pet.model.Pet
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.*

@ExperimentalFoundationApi
@Composable
fun PetListLoadedScreen(
    pets: List<Pet>,
    densitySelector: ListDensity,
    onListDensityClick: (ListDensity) -> Unit,
    navigateToPetDetails: (Long) -> Unit,
    onFeelingLucky: () -> Unit
) {
    Scaffold(
        topBar = {
            MainAppBar(listDensity = densitySelector, onListDensityClick = onListDensityClick)
        },
        bottomBar = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth().background(color = Color(0xDDFFFFFF))) {
                Button(
                    onClick = onFeelingLucky, modifier = Modifier.padding(24.dp).fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.feeling_lucky_button_text),
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 20.sp)
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

                            Column() {
                                Text(
                                    modifier =
                                        Modifier.padding(
                                            start = 16.dp, end = 16.dp, top = 4.dp, bottom = 0.dp),
                                    text = pet.name,
                                    maxLines = 1,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold)
                                Text(
                                    modifier =
                                        Modifier.padding(
                                            start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp),
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
                                Modifier.padding(horizontal = 24.dp, vertical = 4.dp)
                                    .width(128.dp)
                                    .clickable { navigateToPetDetails(pet.id) }) {
                            CoilImage(
                                data = pet.pictureUrl,
                                "Grid",
                                modifier =
                                    Modifier.aspectRatio(1.305f)
                                        .fillMaxWidth()
                                        .clip(
                                            shape = RoundedCornerShape(16.dp),
                                        ),
                                contentScale = ContentScale.Crop)

                            Text(
                                modifier =
                                    Modifier.padding(
                                        start = 16.dp, end = 16.dp, top = 4.dp, bottom = 0.dp),
                                text = pet.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold)
                            Text(
                                modifier =
                                    Modifier.padding(
                                        start = 16.dp, end = 16.dp, top = 0.dp, bottom = 16.dp),
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
