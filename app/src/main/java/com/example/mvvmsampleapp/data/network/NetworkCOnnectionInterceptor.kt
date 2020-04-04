package com.example.mvvmsampleapp.data.network

import android.content.Context
import android.net.ConnectivityManager
import com.example.mvvmsampleapp.utils.NoInternetAvailable
import okhttp3.Interceptor
import okhttp3.Response

class NetworkCOnnectionInterceptor (context: Context): Interceptor {

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isInternetAvailable())
            throw NoInternetAvailable("No Internet Available")

        return chain.proceed(chain.request())

    }
    private fun isInternetAvailable() : Boolean{

        val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.activeNetworkInfo.also {
            return it != null && it.isConnected
        }

    }
}