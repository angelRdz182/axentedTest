package com.itn.myapplicationaxen.core.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import androidx.lifecycle.repeatOnLifecycle

fun <T> Flow<T>.observe(
    lifecycleOwner: LifecycleOwner,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (value: T) -> Unit
) = lifecycleOwner.lifecycleScope.launch {
    lifecycleOwner.repeatOnLifecycle(state) {
        collect { action(it) }
    }
}