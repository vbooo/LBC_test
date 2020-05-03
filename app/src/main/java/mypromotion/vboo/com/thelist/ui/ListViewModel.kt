package mypromotion.vboo.com.thelist.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import mypromotion.vboo.com.thelist.data.ListRepository
import mypromotion.vboo.com.thelist.db.entity.Album
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * This class ask information to the [ListRepository] and give them to the view ([MainActivity] here )
 */
class ListViewModel @Inject constructor(
    private val listRepository: ListRepository
)
: ViewModel() {

    // List of albums
    var albumsResult: MutableLiveData<List<Album>> = MutableLiveData()

    // Eventual error
    var albumsError: MutableLiveData<String> = MutableLiveData()

    /**
     * Ask the album list to the repository
     * If we have a good connection and remote data take less than 500 ms,
     * then we drop the locale album request and get the fresh data from the remote database
     */
    fun loadAlbums() {

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
        listRepository.getAlbums()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe(
                {response -> onResponse(response)},
                {t -> onFailure(t) }
            )
        )
    }

    /**
     * Post error value
     */
    private fun onFailure(t: Throwable?) {
        albumsError.postValue(t?.message)
    }

    /**
     * Post album list
     */
    @Suppress("UNCHECKED_CAST")
    private fun onResponse(response: List<Any>?) {
        val albums: List<Album> = response as List<Album>
        albumsResult.postValue(albums)
    }

}