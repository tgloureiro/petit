import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import br.com.petit.core.ui.UIRoute

@Composable
fun App(navController: NavHostController, startDestination: String, routes: List<UIRoute>) {
    NavHost(navController, startDestination = startDestination) {
        routes.onEach { uiRoute ->
            composable(
                route = uiRoute.route,
                arguments = uiRoute.arguments,
                content = { uiRoute.content(it) })
        }
    }
}
