package br.com.petit.core.ui.util

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver

/*
 * TODO: Remove when fixed by Android
 * Temporary workaround: https://issuetracker.google.com/issues/180042685
 */
fun <T> stateSaver() =
    Saver<MutableState<T>, Any>(
        save = { state -> state.value ?: "null" },
        restore = { value ->
            @Suppress("UNCHECKED_CAST")
            (mutableStateOf((if (value == "null") null else value) as T))
        })
