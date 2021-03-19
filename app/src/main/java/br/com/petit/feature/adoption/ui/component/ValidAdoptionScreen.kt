package br.com.petit.feature.adoption.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.petit.R
import br.com.petit.core.ui.theme.PetitTheme
import br.com.petit.feature.pet.model.Pet
import br.com.petit.feature.pet.model.PetGender
import dev.chrisbanes.accompanist.coil.CoilImage
import java.util.*

@Composable
fun ValidAdoptionScreen(pet: Pet, onBackPressed: () -> Unit, onCancelPressed: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Image(
                        painter = painterResource(R.drawable.ic_logo_app_bar),
                        contentDescription = null,
                    )
                },
                backgroundColor = MaterialTheme.colors.background)
        }) {
        Column(modifier = Modifier.fillMaxHeight().verticalScroll(rememberScrollState())) {
            Text(
                modifier =
                    Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                text =
                    stringResource(
                        R.string.adopted_congratulations, pet.name.capitalize(Locale.getDefault())),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center)
            CoilImage(
                data = pet.pictureUrl,
                contentDescription = null,
                modifier =
                    Modifier.padding(horizontal = 16.dp, vertical = 4.dp)
                        .aspectRatio(1.305f)
                        .widthIn(max = 128.dp)
                        .shadow(8.dp, shape = RoundedCornerShape(16.dp), clip = true),
                contentScale = ContentScale.Crop)

            Image(
                painterResource(R.drawable.map),
                contentDescription = null,
                modifier =
                    Modifier.padding(horizontal = 46.dp, vertical = 24.dp)
                        .aspectRatio(1.69f)
                        .fillMaxWidth()
                        .shadow(8.dp, shape = RoundedCornerShape(16.dp), clip = true),
                contentScale = ContentScale.Crop)

            Text(
                text = stringResource(R.string.delivery_message),
                modifier = Modifier.padding(horizontal = 36.dp),
                textAlign = TextAlign.Center)
            // footer button
            Button(
                onClick = onCancelPressed,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 36.dp).fillMaxWidth(),
                colors =
                    ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.background)) {
                Text(
                    text = stringResource(id = R.string.cancel_button_text),
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 46.dp),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun ValidAdoptionScreenPreview() {
    val pet =
        Pet(
            0,
            "Rudy",
            "https://images.dog.ceo/breeds/spaniel-welsh/n02102177_3639.jpg",
            PetGender.MALE,
            "Description")
    PetitTheme { ValidAdoptionScreen(pet, {}, {}) }
}
