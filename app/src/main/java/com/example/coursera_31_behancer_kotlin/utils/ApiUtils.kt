package com.example.coursera_31_behancer_kotlin.utils

import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

object ApiUtils {

    val NETWORK_EXCEPTIONS: List<Class<*>> = Arrays.asList<Class<out IOException>>(
        UnknownHostException::class.java,
        SocketTimeoutException::class.java,
        ConnectException::class.java
    )
}