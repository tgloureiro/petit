package br.com.petit.core.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

@Composable
fun App(navController: NavHostController, startDestination: String, routes: List<UIRoute>) {
    NavHost(navController, startDestination = startDestination) {
        routes.onEach { uiRoute ->
            composable(
                route = uiRoute.routeName,
                arguments = uiRoute.arguments,
                content = { uiRoute.Content(it) })
        }
    }
}
