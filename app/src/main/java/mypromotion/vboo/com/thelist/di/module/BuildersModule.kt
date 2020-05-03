package mypromotion.vboo.com.thelist.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import mypromotion.vboo.com.thelist.ui.MainActivity

/**
 * This module provides a [MainActivity] instance
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}