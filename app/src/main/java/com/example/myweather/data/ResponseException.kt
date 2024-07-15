package com.example.myweather.data


class ResponseException(
    override val message: String? = null,
    private val throwable: Throwable? = null,
    private val kind: Kind? = null,
) : Throwable(message) {
    enum class Kind {
        PREFERENCE,
    }
    companion object{
    fun preferences(exception: Throwable): ResponseException = ResponseException(
            message = exception.message,
            throwable = exception,
            kind = Kind.PREFERENCE,
        )
    }
}