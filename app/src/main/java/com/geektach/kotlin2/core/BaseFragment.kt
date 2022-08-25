package com.geektach.kotlin2.core

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment : Fragment() {

    protected fun <T> StateFlow<UiState<T>>.subscribe(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: (UiState<T>) -> Unit
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(state) {
                this@subscribe.collect {
                    action(it)
                }
            }
        }
    }
}