package com.astrick.architecture.errorhandling.data

import retrofit2.HttpException

interface AuthRepository {
    suspend fun register(password: String): Resource<User, DataError.Network>
}

data class User(
    val fullName: String,
    val token: String,
    val email: String,
)

class AuthRepositoryImpl: AuthRepository {
    
    override suspend fun register(password: String): Resource<User, DataError.Network> {
        // API call logic
        return try {
            val user = User("dummy", "dummy", "dummy")
            Resource.Success(user)
        } catch(e: HttpException) {
            when(e.code()) {
                408 -> Resource.Error(DataError.Network.RequestTimeout)
                413 -> Resource.Error(DataError.Network.PayloadTooLarge)
                else -> Resource.Error(DataError.Network.Unknown(e.message))
            }
        }
    }
}
