package br.com.petit.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.petit.R

@Composable
fun ImageNotLoaded(){
    Surface(modifier = Modifier.fillMaxSize(),elevation = 4.dp, color = Color(0xFFEFEEFE)) {
        Box(modifier = Modifier.size(96.dp)) {
            Icon(
                painter = painterResource(R.drawable.ic_logo_app_bar),
                null,
                tint = colorResource(R.color.purple_200),
                modifier = Modifier.clip(CircleShape).align(Alignment.Center).height(48.dp)
            )
        }
    }

}