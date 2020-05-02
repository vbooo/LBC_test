package mypromotion.vboo.com.thelist.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import mypromotion.vboo.com.thelist.di.module.AppModule
import mypromotion.vboo.com.thelist.di.module.BuildersModule
import mypromotion.vboo.com.thelist.di.module.NetworkModule
import mypromotion.vboo.com.thelist.di.module.ViewModelModule
import javax.inject.Singleton

/**
 * Indicates the needed modules and inject them into TheListApplication
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class, BuildersModule::class, AppModule::class, ViewModelModule::class, NetworkModule::class]
)

interface AppComponent {
    fun inject(app: TheListApplication)
}