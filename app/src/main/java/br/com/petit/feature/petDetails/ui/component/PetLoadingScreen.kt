package br.com.petit.feature.petDetails.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import br.com.petit.R

@Composable
fun PetLoadingScreen(onBackPressed: () -> Unit) {
    val petLoadingScreenTestTag = stringResource(R.string.pet_loading_screen_test_tag)

    Scaffold(
        topBar = {
            BackAppBar(
                title = stringResource(R.string.pet_loading_screen_title),
                onBackPressed = onBackPressed)
        },
        modifier = Modifier.semantics { testTag = petLoadingScreenTestTag }) {
        Box(Modifier.fillMaxWidth().fillMaxHeight()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}
