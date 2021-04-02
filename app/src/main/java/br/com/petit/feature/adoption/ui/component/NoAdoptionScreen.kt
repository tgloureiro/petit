package br.com.petit.feature.adoption.ui.component

import br.com.petit.feature.petDetails.ui.component.BackAppBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.petit.R
import br.com.petit.core.ui.theme.PetitTheme

@Composable
fun NoAdoptionScreen(onBackPressed: () -> Unit) {
    Scaffold(
        topBar = {
            BackAppBar(
                title = stringResource(id = R.string.no_adoption_screen_title),
                onBackPressed = onBackPressed)
        }) {
        Box(Modifier.fillMaxWidth().fillMaxHeight()) {
            Text(
                stringResource(id = R.string.error_adoption_not_found),
                modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun NoAdoptionScreenPreview() {
    PetitTheme { NoAdoptionScreen {} }
}
