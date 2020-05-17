package net.gahfy.serviceprovider.injection.component

import dagger.Component
import net.gahfy.serviceprovider.injection.module.NetworkModule
import net.gahfy.serviceprovider.viewmodel.PostListViewModel
import net.gahfy.serviceprovider.viewmodel.PostViewModel
import net.gahfy.serviceprovider.viewmodel.LoginViewModel
import net.gahfy.serviceprovider.viewmodel.RegisterViewModel
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param postListViewModel PostListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: PostListViewModel)
    fun inject(loginViewModel: LoginViewModel)
    fun inject(registerViewModel: RegisterViewModel)
    /**
     * Injects required dependencies into the specified PostViewModel.
     * @param postViewModel PostViewModel in which to inject the dependencies
     */
    fun inject(postViewModel: PostViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}