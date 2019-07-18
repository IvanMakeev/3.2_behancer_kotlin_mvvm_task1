package com.example.coursera_31_behancer_kotlin.ui.projects.all_projects

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import android.support.v4.widget.SwipeRefreshLayout
import com.example.coursera_31_behancer_kotlin.BuildConfig
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.data.model.project.ProjectResponse
import com.example.coursera_31_behancer_kotlin.data.model.project.RichProject
import com.example.coursera_31_behancer_kotlin.ui.projects.BaseProjectsViewModel
import com.example.coursera_31_behancer_kotlin.ui.projects.ProjectsAdapter
import com.example.coursera_31_behancer_kotlin.utils.ApiUtils
import io.reactivex.schedulers.Schedulers

class ProjectsViewModel(
    storage: Storage?,
    onItemClickListener: ProjectsAdapter.OnItemClickListener
) : BaseProjectsViewModel(storage, onItemClickListener) {

    init {
        updateProjects()
    }

    override fun updateProjects() {
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
}