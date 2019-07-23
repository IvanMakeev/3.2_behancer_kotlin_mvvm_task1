package com.example.coursera_31_behancer_kotlin.ui.projects.user_projects

import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.data.model.project.ProjectResponse
import com.example.coursera_31_behancer_kotlin.ui.projects.BaseProjectsViewModel
import com.example.coursera_31_behancer_kotlin.utils.ApiUtils
import io.reactivex.schedulers.Schedulers

class UserProjectsViewModel(
    storage: Storage?,
    private val username: String
) : BaseProjectsViewModel(storage) {

    init {
        updateProjects()
        projects = storage!!.getProjectsPagedByName(username)
    }

    override fun updateProjects() {
        disposable = ApiUtils.getApiService().getUserProjects(username)
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