package com.astrick.compose.agnostic

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * A JUnit `TestWatcher` rule to set up and manage the coroutine dispatcher for testing purposes.
 *
 * This rule replaces the default `Dispatchers.Main` with a test dispatcher, ensuring that coroutine-based
 * code is properly tested in a controlled environment. It also resets the main dispatcher after the test completes.
 *
 * @property dispatcher The `CoroutineDispatcher` to use during tests. Defaults to `StandardTestDispatcher`.
 */
@Suppress("unused")
@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineScopeRule(
    val dispatcher: CoroutineDispatcher = StandardTestDispatcher()
): TestWatcher() {
    
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(dispatcher)
    }
    
    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
    
}
