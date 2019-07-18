package com.example.coursera_31_behancer_kotlin.ui.projects

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.v4.widget.SwipeRefreshLayout
import com.example.coursera_31_behancer_kotlin.BuildConfig
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.data.model.project.ProjectResponse
import com.example.coursera_31_behancer_kotlin.data.model.project.RichProject
import com.example.coursera_31_behancer_kotlin.utils.ApiUtils
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectsViewModel(
    private var storage: Storage?,
    val onItemClickListener: ProjectsAdapter.OnItemClickListener
) : ViewModel() {

    private var disposable: Disposable? = null
    val isLoading = MutableLiveData<Boolean>()
    val isErrorVisible = MutableLiveData<Boolean>()
    val projects: LiveData<List<RichProject>> = storage!!.getProjectsLive()
    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        updateProjects()
    }

    init {
        updateProjects()
    }

    private fun updateProjects() {
        disposable = ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY)
            .map(ProjectResponse::projects)
            .doOnSuccess { isErrorVisible.postValue(false) }
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { isLoading.postValue(true) }
            .doFinally { isLoading.postValue(false) }
            .subscribe(
                { response ->
                    storage!!.insertProjects(response)
                },
                {
                    isErrorVisible.postValue(true)
                    val value = projects.value == null || projects.value!!.isEmpty()
                    isErrorVisible.postValue(value)
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