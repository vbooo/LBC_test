package mypromotion.vboo.com.thelist.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import io.reactivex.Observable
import mypromotion.vboo.com.thelist.utils.InternetCheck
import mypromotion.vboo.com.thelist.db.dao.AlbumDao
import mypromotion.vboo.com.thelist.db.entity.Album
import javax.inject.Inject

/**
 * This class interacts with the remote and local data.
 * The viewModel ask informations to this repository
 */
class ListRepository @Inject constructor(private val albumDao: AlbumDao, private val albumApi: AlbumAPI, private val internetCheck: InternetCheck) {

    fun getAlbums(): Observable<List<Any>> {
        val observableFromApi = getAlbumsFromApi()
        val observableFromDb = getAlbumsFromDb()
        return if (internetCheck.isConnectedToNetwork()) {
            Observable.concatArray(observableFromDb, observableFromApi)
        } else {
            Observable.concatArray(observableFromDb)
        }
    }

    /**
     * Get all remote albums list
     */
    private fun getAlbumsFromApi(): Observable<List<Album>> {
        return albumApi.readJson().map {
                listJson: List<JsonObject>? ->
            // get the json string from the response
            val jsonString = listJson.toString()

            val collectionType = object : TypeToken<Collection<Album>>() {}.type

            // parse json from string to Album's collection
            val albumCollection: Collection<Album> = Gson().fromJson(jsonString, collectionType)

            // Insert each album in the locale database
            for (item in albumCollection.toList()) {
                 albumDao.insert(item)
            }

            // parse a List from a Collection and return it
            albumCollection.toList()
        }

    }

    /**
     * Get all remote albums list
     */
    private fun getAlbumsFromDb(): Observable<List<Album>> {
        return albumDao.getAll()
            .toObservable()
            .doOnNext {
                //Print log it.size :)
                Log.e("DB albums number: ", it.size.toString())
            }
    }


}