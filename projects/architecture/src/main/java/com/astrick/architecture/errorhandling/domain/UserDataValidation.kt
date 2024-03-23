package com.astrick.architecture.errorhandling.domain

import com.astrick.architecture.errorhandling.data.Resource
import com.astrick.architecture.errorhandling.data.Error

class UserDataValidator {
    
    fun validatePassword(password: String): Resource<Unit, PasswordError> {
        if(password.length < 9) {
            return Resource.Error(PasswordError.TOO_SHORT)
        }
        
        val hasUppercaseChar = password.any { it.isUpperCase() }
        if(!hasUppercaseChar) {
            return Resource.Error(PasswordError.NO_UPPERCASE)
        }
        
        val hasDigit = password.any { it.isDigit() }
        if(!hasDigit) {
            return Resource.Error(PasswordError.NO_DIGIT)
        }
        
        return Resource.Success(Unit)
    }
    
    enum class PasswordError: Error {
        TOO_SHORT,
        NO_UPPERCASE,
        NO_DIGIT
    }
    
}
