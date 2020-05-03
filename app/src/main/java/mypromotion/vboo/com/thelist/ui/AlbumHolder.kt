package mypromotion.vboo.com.thelist.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album.view.*
import mypromotion.vboo.com.thelist.R

/**
 * This class handles albums informations for each line of the list
 */
class AlbumHolder(var view: View) : RecyclerView.ViewHolder(view) {

    /**
     * Set Album title
     */
    fun setAlbumTitle(value: String) {
       view.item_album_title.text = value
    }

    /**
     * Set Album thumbnail image
     */
    fun setAlbumThumbnail(url: String?) {
        Picasso.get().load(url).error(R.drawable.electrician).placeholder(
            R.drawable.default_img
        ).into(view.item_album_thumbnail)
    }

}