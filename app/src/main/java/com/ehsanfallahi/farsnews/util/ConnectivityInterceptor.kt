package com.ehsanfallahi.farsnews.util

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import com.ehsanfallahi.farsnews.util.Constant.Companion.MY_TAG
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(context: Context) : Interceptor {
    private val appContext=context.applicationContext
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isOnline())
        {
            Log.i(MY_TAG,"no internet connection")
            throw NoConnectivityException()
        }
            return chain.proceed(chain.request())
    }

    private fun isOnline():Boolean{
        val connectivityManager=appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager

        val networkInfo=connectivityManager.activeNetworkInfo

        return networkInfo!=null && networkInfo.isConnected
    }
}