package br.com.petit.feature.adoption.ui.component

import BackAppBar
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import br.com.petit.R
import br.com.petit.core.ui.theme.PetitTheme

@Composable
fun LoadingAdoptionScreen(onBackPressed: () -> Unit) {
    val adoptionLoadingScreenTestTag = stringResource(R.string.adoption_loading_screen_test_tag)
    Scaffold(
        topBar = {
            BackAppBar(
                title = stringResource(R.string.adoption_loading_screen_title),
                onBackPressed = onBackPressed)
        },
        modifier = Modifier.semantics { testTag = adoptionLoadingScreenTestTag }) {
        Box(Modifier.fillMaxWidth().fillMaxHeight()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalFoundationApi
fun AdoptionLoadingScreenPreview() {
    PetitTheme { LoadingAdoptionScreen {} }
}
