import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.petit.R

@Composable
fun BackAppBar(title: String, onBackPressed: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            IconButton(
                onClick = onBackPressed, modifier = Modifier.semantics { testTag = "backButton" }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.app_name))
            }
        }
        Text(
            modifier = Modifier.align(Alignment.CenterVertically).padding(horizontal = 24.dp),
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333))
    }
}
