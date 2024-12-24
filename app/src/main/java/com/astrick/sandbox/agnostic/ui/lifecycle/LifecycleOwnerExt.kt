package com.astrick.sandbox.agnostic.ui.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

@Suppress("unused")
object LifecycleOwnerExt {

    /**
     * Launches a coroutine that runs the provided [block] every time the LifecycleOwner's lifecycle
     * is in the CREATED state. The coroutine is automatically canceled when the lifecycle state changes.
     *
     * @param block The suspend function to execute while in the CREATED state.
     */
    inline fun LifecycleOwner.repeatOnCreated(crossinline block: suspend () -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                block()
            }
        }
    }

    /**
     * Launches a coroutine that runs the provided [block] every time the LifecycleOwner's lifecycle
     * is in the RESUMED state. The coroutine is automatically canceled when the lifecycle state changes.
     *
     * @param block The suspend function to execute while in the RESUMED state.
     */
    inline fun LifecycleOwner.repeatOnResume(crossinline block: suspend () -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                block()
            }
        }
    }

    /**
     * Launches a coroutine that runs the provided [block] every time the LifecycleOwner's lifecycle
     * is in the STARTED state. The coroutine is automatically canceled when the lifecycle state changes.
     *
     * @param block The suspend function to execute while in the STARTED state.
     */
    inline fun LifecycleOwner.repeatOnStarted(crossinline block: suspend () -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                block()
            }
        }
    }

}
