package com.example.coursera_31_behancer_kotlin.ui.profile

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.data.model.user.User
import com.example.coursera_31_behancer_kotlin.data.model.user.UserResponse
import com.example.coursera_31_behancer_kotlin.utils.ApiUtils
import com.example.coursera_31_behancer_kotlin.utils.DateUtils
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(
    private var storage: Storage?,
    private var username: String
) : ViewModel() {

    private var disposable: Disposable? = null
    var onClickListener: View.OnClickListener? = null
    val isLoading = ObservableBoolean(false)
    val isErrorVisible = ObservableBoolean(false)
    val profileImageUrl: ObservableField<String> = ObservableField()
    val profileName: ObservableField<String> = ObservableField()
    val profileCreatedOn: ObservableField<String> = ObservableField()
    val profileLocation: ObservableField<String> = ObservableField()
    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        loadProfile()
    }

    fun loadProfile() {
        disposable = ApiUtils.getApiService().getUserInfo(username)
            .map(UserResponse::user)
            .doOnSuccess { isErrorVisible.set(false) }
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe(
                { user ->
                    storage!!.insertUser(user)
                    bind(user)
                },
                {
                    isErrorVisible.set(true)
                })
    }

    private fun bind(user: User) {
        profileImageUrl.set(user.image!!.photoUrl)
        profileName.set(user.displayName)
        profileCreatedOn.set(DateUtils.format(user.createdOn))
        profileLocation.set(user.location)

    }

    fun openAllProject() = onClickListener

    override fun onCleared() {
        super.onCleared()
        storage = null
        disposable?.let { it.dispose() }
    }
}