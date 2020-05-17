package net.app.serviceprovider.injection.component

import dagger.Component
import net.app.serviceprovider.injection.module.NetworkModule
import net.app.serviceprovider.viewmodel.PostListViewModel
import net.app.serviceprovider.viewmodel.PostViewModel
import net.app.serviceprovider.viewmodel.RegisterViewModel
import net.app.serviceprovider.viewmodel.LoginViewModel
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