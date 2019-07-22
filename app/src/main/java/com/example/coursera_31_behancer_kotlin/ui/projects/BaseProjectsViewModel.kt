package com.example.coursera_31_behancer_kotlin.ui.projects

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.support.v4.widget.SwipeRefreshLayout
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.data.model.project.RichProject
import io.reactivex.disposables.Disposable

abstract class BaseProjectsViewModel(
    var storage: Storage?,
    val onItemClickListener: ProjectsAdapter.OnItemClickListener
) : ViewModel() {
    protected var disposable: Disposable? = null
    val isLoading = MutableLiveData<Boolean>()
    val isErrorVisible = MutableLiveData<Boolean>()
    var projects: LiveData<PagedList<RichProject>>
    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        updateProjects()
    }

    init {
       projects = storage!!.getProjectsPaged()
    }

    abstract fun updateProjects()

    override fun onCleared() {
        super.onCleared()
        storage = null
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}