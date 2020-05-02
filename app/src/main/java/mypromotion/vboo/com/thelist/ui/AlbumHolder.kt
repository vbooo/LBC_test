package mypromotion.vboo.com.thelist.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_album.view.*
import mypromotion.vboo.com.thelist.R

/**
 *
 */
class AlbumHolder(var view: View) : RecyclerView.ViewHolder(view) {

    fun setAlbumTitle(value: String) {
       view.item_album_title.text = value
    }

    // set user points
    fun setAlbumThumbnail(url: String?) {
        Picasso.get().load(url).error(R.drawable.electrician).placeholder(
            R.drawable.default_img
        )
            .into(view.item_album_thumbnail)
    }

}