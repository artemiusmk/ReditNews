package com.reddit.presentation.ui.global

import android.arch.lifecycle.*
import android.os.Bundle
import io.reactivex.functions.Cancellable
import javax.inject.Inject

@Suppress("unused")
abstract class AbsMvvmActivity<VM : ViewModel> : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: VM

    abstract fun initInjection()

    abstract fun provideViewModelClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInjection()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(provideViewModelClass())
    }

    protected fun <T> LiveData<T>.observeLiveData(action: (data: T?) -> Unit): Cancellable {
        val observer = Observer<T> { action(it) }
        this.observe(this@AbsMvvmActivity, observer)
        return Cancellable { this.removeObserver(observer) }
    }

    protected fun <T> LiveData<T>.observeLiveDataNullSafety(action: (data: T) -> Unit): Cancellable {
        val observer = Observer<T> { data -> data?.let(action) }
        this.observe(this@AbsMvvmActivity, observer)
        return Cancellable { this.removeObserver(observer) }
    }
}