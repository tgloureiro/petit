package br.com.petit.feature.pet.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import br.com.petit.R

@Composable
fun PetListLoadingScreen() {
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
        Box(Modifier.fillMaxWidth().fillMaxHeight()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }
}
