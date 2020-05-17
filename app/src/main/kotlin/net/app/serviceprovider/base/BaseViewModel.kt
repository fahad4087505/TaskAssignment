package net.gahfy.serviceprovider.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.gahfy.serviceprovider.injection.component.DaggerViewModelInjector
import net.gahfy.serviceprovider.injection.component.ViewModelInjector
import net.gahfy.serviceprovider.injection.module.NetworkModule
import net.gahfy.serviceprovider.viewmodel.PostListViewModel
import net.gahfy.serviceprovider.viewmodel.PostViewModel
import net.gahfy.serviceprovider.viewmodel.LoginViewModel
import net.gahfy.serviceprovider.viewmodel.RegisterViewModel

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