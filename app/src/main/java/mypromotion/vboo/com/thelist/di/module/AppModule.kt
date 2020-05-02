package mypromotion.vboo.com.thelist.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import mypromotion.vboo.com.thelist.db.AppDatabase
import mypromotion.vboo.com.thelist.db.dao.AlbumDao
import javax.inject.Singleton

/**
 * This module provides Application, AppDatabase and AlbumDao objects
 */
@Module
class AppModule(private val app: Application){

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "listDB")
            .build()
    }

    @Provides
    @Singleton
    fun provideAlbumDao(database: AppDatabase): AlbumDao = database.albumDao()

}