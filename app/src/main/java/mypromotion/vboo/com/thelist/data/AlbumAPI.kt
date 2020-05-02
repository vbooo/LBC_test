package mypromotion.vboo.com.thelist.data

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import mypromotion.vboo.com.thelist.db.entity.*

/**
 * Web service interface for handle the [Album] informations
 */
interface AlbumAPI {

    @GET("img/shared/technical-test.json")
    fun readJson(): Observable<List<JsonObject>>
}