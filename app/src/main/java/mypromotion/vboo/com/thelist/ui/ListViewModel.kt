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


class ListViewModel @Inject constructor(
    private val listRepository: ListRepository
)
: ViewModel() {

    var albumsResult: MutableLiveData<List<Album>> = MutableLiveData()
    var albumsError: MutableLiveData<String> = MutableLiveData()


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

    private fun onFailure(t: Throwable?) {
        albumsError.postValue(t?.message)
    }

    @Suppress("UNCHECKED_CAST")
    private fun onResponse(response: List<Any>?) {
        val albums: List<Album> = response as List<Album>
        albumsResult.postValue(albums)
    }

}