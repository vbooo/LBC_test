package mypromotion.vboo.com.thelist.ui

import android.content.Context
import android.view.View
import mypromotion.vboo.com.thelist.R
import mypromotion.vboo.com.thelist.db.entity.Album


/**
 * This class provides UI related informations to avoid put this kind of logic in the MainActivity
 */
class MainActivityViewModelHelper(var context: Context, var listAlbum: List<Album>, var isError: Boolean = false, var isConnectedToNetwork: Boolean) {

    /**
     * Get the ProgressBar visibility
     */
    fun getProgressBarVisibility(): Int {
        return if (listAlbum.isEmpty() && !isConnectedToNetwork || listAlbum.isNotEmpty() || isError) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    /**
     * Get the message value
     */
    fun getMessageValue(): String? {
        return if (listAlbum.isEmpty() && !isConnectedToNetwork) {
            context.resources.getString(R.string.no_item_message)
        } else if (isError){
            context.resources.getString(R.string.error_message)
        } else {
            null
        }
    }

    /**
     * Get the message visibility
     */
    fun getMessageVisibility(): Int {
        return if (isError || (listAlbum.isEmpty() && !isConnectedToNetwork)){
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}