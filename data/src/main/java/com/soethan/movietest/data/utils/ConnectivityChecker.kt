package com.soethan.movietest.data.utils

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityChecker  constructor(private val context: Context) {

    fun isNetworkAvailable(): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        return network != null
    }

}