import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.petit.R
import br.com.petit.core.ui.util.ListDensity

@Composable
fun MainAppBar(
    listDensity: ListDensity,
    onListDensityClick: (listDensity: ListDensity) -> Unit,
    onSearchClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Image(
                painter = painterResource(R.drawable.ic_logo_app_bar),
                contentDescription = "menu",
            )
        },
        actions = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Crossfade(targetState = listDensity) {
                    when (it) {
                        ListDensity.ONE -> {
                            Icon(
                                painterResource(id = R.drawable.ic_selected_grid),
                                "Grid",
                                tint = Color(0xFFB2B2B2),
                                modifier =
                                    Modifier.padding(horizontal = 12.dp).clickable {
                                        onListDensityClick(ListDensity.TWO)
                                    })
                        }
                        ListDensity.TWO -> {
                            Image(
                                painterResource(id = R.drawable.ic_selected_grid),
                                "Grid",
                                Modifier.padding(horizontal = 12.dp).clickable {
                                    onListDensityClick(ListDensity.TWO)
                                })
                        }
                    }
                }

                Crossfade(targetState = listDensity) {
                    when (it) {
                        ListDensity.ONE -> {
                            Image(
                                painterResource(id = R.drawable.ic_selected_list),
                                "List",
                                Modifier.clickable(
                                        onClick = { onListDensityClick(ListDensity.ONE) })
                                    .padding(horizontal = 12.dp))
                        }
                        ListDensity.TWO -> {
                            Icon(
                                painterResource(id = R.drawable.ic_selected_list),
                                "List",
                                tint = Color(0xFFB2B2B2),
                                modifier =
                                    Modifier.clickable(
                                            onClick = { onListDensityClick(ListDensity.ONE) })
                                        .padding(horizontal = 12.dp))
                        }
                    }
                }
            }
        },
        backgroundColor = MaterialTheme.colors.background)
}
