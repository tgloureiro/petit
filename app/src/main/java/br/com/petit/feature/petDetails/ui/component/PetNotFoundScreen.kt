package br.com.petit.feature.petDetails.ui.component

import BackAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import br.com.petit.R

@Composable
fun PetNotFoundScreen(onBackPressed: () -> Unit) {
    val petNotFoundScreenTestTag = stringResource(R.string.pet_not_found_screen_test_tag)
    Scaffold(
        topBar = {
            BackAppBar(
                title = stringResource(R.string.error_loading_pet), onBackPressed = onBackPressed)
        },
        modifier = Modifier.semantics { testTag = petNotFoundScreenTestTag }) {
        Text(text = stringResource(R.string.pet_not_found))
    }
}
