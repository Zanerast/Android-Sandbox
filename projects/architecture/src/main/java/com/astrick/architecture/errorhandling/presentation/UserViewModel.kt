package com.astrick.architecture.errorhandling.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.astrick.architecture.errorhandling.data.AuthRepository
import com.astrick.architecture.errorhandling.data.Resource
import com.astrick.architecture.errorhandling.domain.UserDataValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userDataValidator: UserDataValidator,
    private val repository: AuthRepository
): ViewModel() {
    
    private val eventChannel = Channel<UserEvent>()
    val events = eventChannel.receiveAsFlow()
    
    fun onRegisterClick(password: String) {
        when(val result = userDataValidator.validatePassword(password)) {
            is Resource.Error -> {
                when(result.error) {
                    UserDataValidator.PasswordError.TOO_SHORT -> TODO()
                    UserDataValidator.PasswordError.NO_UPPERCASE -> TODO()
                    UserDataValidator.PasswordError.NO_DIGIT -> TODO()
                }
            }
            is Resource.Success -> {
            
            }
        }
        
        viewModelScope.launch {
            when(val result = repository.register(password)) {
                is Resource.Error -> {
                    val errorMessage = result.asErrorUiText()
                    eventChannel.send(UserEvent.Error(errorMessage))
                }
                is Resource.Success -> {
                    result.data
                }
            }
        }
    }
}

sealed interface UserEvent {
    data class Error(val error: UiText): UserEvent
}
