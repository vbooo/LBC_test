package mypromotion.vboo.com.thelist.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import mypromotion.vboo.com.thelist.ui.ListViewModel
import mypromotion.vboo.com.thelist.ui.ListViewModelFactory
import kotlin.reflect.KClass

/**
 * This modules provides [ViewModelProvider.Factory] and [ViewModel] instances
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ListViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    internal abstract fun postListViewModel(viewModel: ListViewModel): ViewModel

}