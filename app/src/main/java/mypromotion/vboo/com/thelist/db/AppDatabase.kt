package mypromotion.vboo.com.thelist.db

import androidx.room.Database
import androidx.room.RoomDatabase
import mypromotion.vboo.com.thelist.db.dao.AlbumDao
import mypromotion.vboo.com.thelist.db.entity.Album


@Database(entities = [Album::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}