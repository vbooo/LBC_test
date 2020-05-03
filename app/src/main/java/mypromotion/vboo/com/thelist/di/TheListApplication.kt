package mypromotion.vboo.com.thelist.di

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import mypromotion.vboo.com.thelist.BuildConfig
import mypromotion.vboo.com.thelist.di.module.AppModule
import mypromotion.vboo.com.thelist.di.module.NetworkModule
import javax.inject.Inject

/**
 * Our custom Application class to inject our dependences
 */
class TheListApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(BuildConfig.URL, this))
            .build()
            .inject(this)
    }


    override fun androidInjector(): AndroidInjector<Any> = activityInjector

}