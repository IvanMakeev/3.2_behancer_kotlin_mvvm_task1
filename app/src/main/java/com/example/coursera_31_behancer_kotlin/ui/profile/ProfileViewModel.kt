package com.example.coursera_31_behancer_kotlin.ui.profile

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.support.v4.widget.SwipeRefreshLayout
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.data.api.BehanceApi
import com.example.coursera_31_behancer_kotlin.data.model.user.User
import com.example.coursera_31_behancer_kotlin.utils.ApiUtils
import com.example.coursera_31_behancer_kotlin.utils.DateUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfileViewModel
@Inject constructor(
    private var storage: Storage,
    private var api: BehanceApi
) {

    private var disposable: Disposable? = null
    private lateinit var username: String
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
        disposable = api.getUserInfo(username)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response -> storage.insertUser(response) }
            .onErrorReturn { throwable ->
                if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable::class.java))
                    storage.getUser(username)
                else
                    null
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.set(true) }
            .doFinally { isLoading.set(false) }
            .subscribe(
                { response ->
                    isErrorVisible.set(false)
                    bind(response.user)
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

    fun setUsername(username: String) {
        this.username = username
    }

    fun dispatchDetach() {
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}