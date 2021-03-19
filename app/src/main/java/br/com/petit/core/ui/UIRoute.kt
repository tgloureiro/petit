package br.com.petit.core.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NamedNavArgument

abstract class UIRoute() {
    abstract val route: String
    abstract val arguments: List<NamedNavArgument>
    @Composable abstract fun Content(navBackStackEntry: NavBackStackEntry)
}
