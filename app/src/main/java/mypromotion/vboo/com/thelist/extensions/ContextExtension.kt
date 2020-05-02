package mypromotion.vboo.com.thelist.extensions

import android.content.Context
import android.net.ConnectivityManager

    /**
     * Check if we have a network connection
     */
    fun Context.isConnectedToNetwork(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }
