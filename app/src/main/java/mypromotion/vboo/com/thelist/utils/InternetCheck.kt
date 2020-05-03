package mypromotion.vboo.com.thelist.utils

import android.content.Context
import android.net.ConnectivityManager

class InternetCheck(var context: Context) {

    /**
     * Check if we have a network connection
     */
    fun isConnectedToNetwork(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
    }
}