package com.astrick.architecture.errorhandling.data

interface Error

sealed interface DataError : Error {
    
    sealed class Network : DataError {
        object RequestTimeout : Network()
        object TooManyRequests : Network()
        object NoInternet : Network()
        object PayloadTooLarge : Network()
        object ServerError : Network()
        object Serialization : Network()
        data class Unknown(val message: String? = null): Network()
    }
    
    enum class Local : DataError {
        DISK_FULL
    }
}
