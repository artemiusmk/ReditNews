package com.reddit.presentation.mvp.global

import io.reactivex.CompletableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@Suppress("unused")
class SchedulersProvider @Inject constructor() {

    fun ioToMainTransformerCompletable(): CompletableTransformer {
        return CompletableTransformer { observable ->
            observable
                    .subscribeOn(io())
                    .observeOn(ui())
        }
    }

    fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    fun computation(): Scheduler {
        return Schedulers.computation()
    }

    fun io(): Scheduler {
        return Schedulers.io()
    }

    fun <T> getIOToMainTransformer(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable
                    .subscribeOn(io())
                    .observeOn(ui())
        }
    }

    fun <T> getIOToMainTransformerSingle(): SingleTransformer<T, T> {
        return SingleTransformer { observable ->
            observable
                    .subscribeOn(io())
                    .observeOn(ui())
        }
    }
}
