package net.app.serviceprovider.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.app.serviceprovider.injection.component.DaggerViewModelInjector
import net.app.serviceprovider.injection.component.ViewModelInjector
import net.app.serviceprovider.injection.module.NetworkModule
import net.app.serviceprovider.viewmodel.PostListViewModel
import net.app.serviceprovider.viewmodel.PostViewModel
import net.app.serviceprovider.viewmodel.LoginViewModel
import net.app.serviceprovider.viewmodel.RegisterViewModel

abstract class BaseViewModel(application: Application):AndroidViewModel(application){
    private val injector: ViewModelInjector = DaggerViewModelInjector.builder().networkModule(NetworkModule)
            .build()
    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
            is PostViewModel -> injector.inject(this)
            is LoginViewModel -> injector.inject(this)
            is RegisterViewModel -> injector.inject(this)
        }
    }
}