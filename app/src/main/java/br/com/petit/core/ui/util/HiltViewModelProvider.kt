package br.com.petit.core.ui.util

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry

@Composable
inline fun <reified T : ViewModel> hiltViewModel(
    context: Context,
    navBackStackEntry: NavBackStackEntry
): T = viewModel(factory = HiltViewModelFactory(context, navBackStackEntry))
