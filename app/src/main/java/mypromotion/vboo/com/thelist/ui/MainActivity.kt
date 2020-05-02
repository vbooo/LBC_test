package mypromotion.vboo.com.thelist.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import mypromotion.vboo.com.thelist.R
import mypromotion.vboo.com.thelist.db.entity.Album
import mypromotion.vboo.com.thelist.extensions.isConnectedToNetwork
import javax.inject.Inject

/**
 * This activity displays the [Album] list
 */
class MainActivity : AppCompatActivity() {


    companion object {
        fun Context.isConnectedToNetwork(): Boolean {
            val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting ?: false
        }
    }

    // Factory needed to create our ViewModel with custom parameter
    @Inject
    lateinit var albumViewModelFactory: ViewModelProvider.Factory

    lateinit var mainActivityViewModelHelper: MainActivityViewModelHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Process to the dependance injection
        AndroidInjection.inject(this)

        // Implement the AlbumAdapter
        val viewAdapter = AlbumAdapter()

        // Set our recycler view which will contain our Album list
        activity_main_recycler_view.apply {
            setHasFixedSize(true)
            this.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = viewAdapter
        }

        // Implement the MainActivityViewModelHelper
        mainActivityViewModelHelper = MainActivityViewModelHelper(this, listOf(), false, isConnectedToNetwork())

        // Implement the ListViewModel with the ViewModelProvider.Factory help
        val listViewModel: ListViewModel by viewModels { albumViewModelFactory }

        // Ask the viewModel to load all the albums
        listViewModel.loadAlbums()

        // Observe the result of the loadAlbum request
        listViewModel.albumsResult.observe(this,
            Observer<List<Album>> {

                // Update the mainActivityViewModelHelper informations and then update the UI
                mainActivityViewModelHelper.listAlbum = it
                mainActivityViewModelHelper.isError = false
                mainActivityViewModelHelper.isConnectedToNetwork = isConnectedToNetwork()
                updateUI()

                // Fill the albums list adapter
                viewAdapter.listAlbums = it

            })

        // Observe an eventual loadAlbum request error
        listViewModel.albumsError.observe(this,
            Observer<String> {
                // Log the error label
                Log.e("Get albums error: ", it.toString())

                // Update the mainActivityViewModelHelper informations and then update the UI
                mainActivityViewModelHelper.isError = true
                mainActivityViewModelHelper.isConnectedToNetwork = isConnectedToNetwork()
                updateUI()
            })
    }

    /**
     * Update the UI related informations
     */
    private fun updateUI() {
        activity_main_progress_bar.visibility = mainActivityViewModelHelper.getProgressBarVisibility()
        activity_main_message.visibility = mainActivityViewModelHelper.getMessageVisibility()
        activity_main_message.text = mainActivityViewModelHelper.getMessageValue()
    }

}
