package mypromotion.vboo.com.thelist.di.module

import dagger.Module
import dagger.Provides
import mypromotion.vboo.com.thelist.data.AlbumAPI
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This module provides Retrofit and AlbumAPI instances
 */
@Module
class NetworkModule(private val baseUrl: String) {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesAlbumApi(retrofit: Retrofit): AlbumAPI = retrofit.create(
        AlbumAPI::class.java)
}