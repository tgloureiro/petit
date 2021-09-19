package br.com.petit.core.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry

abstract class UIRoute {
    abstract val routeName: String
    abstract val arguments: List<NamedNavArgument>
    @Composable abstract fun Content(navBackStackEntry: NavBackStackEntry)
}
