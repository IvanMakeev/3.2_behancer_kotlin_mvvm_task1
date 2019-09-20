package com.example.coursera_31_behancer_kotlin.ui.projects

import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.support.v4.widget.SwipeRefreshLayout
import com.example.coursera_31_behancer_kotlin.BuildConfig
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.data.api.BehanceApi
import com.example.coursera_31_behancer_kotlin.data.model.project.Project
import com.example.coursera_31_behancer_kotlin.utils.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProjectsViewModel
 @Inject constructor(
    private var storage: Storage,
    private val api: BehanceApi
) {

    private var disposable: Disposable? = null
    val isLoading = ObservableBoolean(false)
    val isErrorVisible = ObservableBoolean(false)
    val projects = ObservableArrayList<Project>()
    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        loadProjects()
    }
    var onItemClickListener: ProjectsAdapter.OnItemClickListener? = null


    fun loadProjects() {
        disposable = api.getProjects(BuildConfig.API_QUERY)
            .doOnSuccess { response -> storage.insertProjects(response) }
            .onErrorReturn { throwable ->
                if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable::class.java))
                    storage.getProjects()
                else null
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe(
                { response ->
                    isErrorVisible.set(false)
                    projects.addAll(response.projects)
                },
                {
                    isErrorVisible.set(true)
                })
    }

    fun dispatchDetach() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}