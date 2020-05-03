package mypromotion.vboo.com.thelist.db.dao

import androidx.room.*
import io.reactivex.Single
import mypromotion.vboo.com.thelist.db.entity.Album

/**
 * [Album] DAO
 */
@Dao
interface AlbumDao {

    @Query("SELECT * FROM album")
    fun getAll(): Single<List<Album>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: Album)

    @Delete
    fun delete(action: Album)

    @Update
    fun update(action: Album)

}