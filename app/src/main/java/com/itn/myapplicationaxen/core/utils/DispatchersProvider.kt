package com.itn.myapplicationaxen.core.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

interface DispatchersProvider {
    val main: CoroutineDispatcher get() = Dispatchers.Main
    val io: CoroutineDispatcher get() = Dispatchers.IO
    val default: CoroutineDispatcher get() = Dispatchers.Default
    val unconfined: CoroutineDispatcher get() = Dispatchers.Unconfined
}

class DefaultDispatchersProvider @Inject constructor(): DispatchersProvider