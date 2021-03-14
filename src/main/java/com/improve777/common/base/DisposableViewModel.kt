package com.improve777.common.base

import io.reactivex.disposables.CompositeDisposable

abstract class DisposableViewModel : BaseViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}