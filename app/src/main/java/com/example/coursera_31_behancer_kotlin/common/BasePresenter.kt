package com.example.coursera_31_behancer_kotlin.common

import io.reactivex.disposables.CompositeDisposable

class BasePresenter {

    protected val compositeDisposable = CompositeDisposable()

    fun disposeAll() {
        compositeDisposable.clear()
    }
}