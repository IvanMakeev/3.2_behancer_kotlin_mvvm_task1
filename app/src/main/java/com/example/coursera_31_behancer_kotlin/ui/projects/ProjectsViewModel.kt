package com.example.coursera_31_behancer_kotlin.ui.projects

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.support.v4.widget.SwipeRefreshLayout
import com.example.coursera_31_behancer_kotlin.BuildConfig
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.data.model.project.Project
import com.example.coursera_31_behancer_kotlin.utils.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectsViewModel(
    private var storage: Storage?,
    val onItemClickListener: ProjectsAdapter.OnItemClickListener
) : ViewModel() {

    private var disposable: Disposable? = null
    val isLoading = MutableLiveData<Boolean>()
    val isErrorVisible = MutableLiveData<Boolean>()
    val projects = MutableLiveData<List<Project>>()
    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        loadProjects()
    }

    init {
        projects.value = ArrayList()
        loadProjects()
    }

    fun loadProjects() {
        disposable = ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY)
            .doOnSuccess { response -> storage!!.insertProjects(response) }
            .onErrorReturn { throwable -> if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable::class.java)) storage!!.getProjects() else null }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.postValue(true) }
            .doFinally { isLoading.postValue(false) }
            .subscribe(
                { response ->
                    isErrorVisible.postValue(false)
                    projects.postValue(response.projects)
                },
                { throwable ->
                    isErrorVisible.postValue(true)
                })
    }

    override fun onCleared() {
        super.onCleared()
        storage = null
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}