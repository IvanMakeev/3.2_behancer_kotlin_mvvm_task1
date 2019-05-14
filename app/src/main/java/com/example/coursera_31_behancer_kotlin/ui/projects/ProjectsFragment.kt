package com.example.coursera_31_behancer_kotlin.ui.projects

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coursera_31_behancer_kotlin.BuildConfig
import com.example.coursera_31_behancer_kotlin.R
import com.example.coursera_31_behancer_kotlin.common.RefreshOwner
import com.example.coursera_31_behancer_kotlin.common.Refreshable
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.ui.profile.ProfileActivity
import com.example.coursera_31_behancer_kotlin.ui.profile.ProfileFragment
import com.example.coursera_31_behancer_kotlin.utils.ApiUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProjectsFragment : Fragment(), Refreshable, ProjectsAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var errorView: View
    private var refreshOwner: RefreshOwner? = null
    private var storage: Storage? = null
    private var projectsAdapter: ProjectsAdapter? = null
    private var disposable: Disposable? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Storage.StorageOwner) storage = context.obtainStorage()

        if (context is RefreshOwner) refreshOwner = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fr_projects, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler)
        errorView = view.findViewById(R.id.errorView)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            activity!!.setTitle(R.string.projects)
        }

        projectsAdapter = ProjectsAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = projectsAdapter

        onRefreshData()
    }

    override fun onItemClick(username: String) {
        val intent = Intent(activity, ProfileActivity::class.java)
        val args = Bundle()
        args.putString(ProfileFragment.PROFILE_KEY, username)
        intent.putExtra(ProfileActivity.USERNAME_KEY, args)
        startActivity(intent)
    }

    override fun onDetach() {
        storage = null
        refreshOwner = null
        if (disposable != null) {
            disposable!!.dispose()
        }
        super.onDetach()
    }

    override fun onRefreshData() {
        getProjects()
    }

    private fun getProjects() {
        disposable = ApiUtils.getApiService().getProjects(BuildConfig.API_QUERY)
            .doOnSuccess { response -> storage!!.insertProjects(response) }
            .onErrorReturn { throwable -> if (ApiUtils.NETWORK_EXCEPTIONS.contains(throwable::class.java)) storage!!.getProjects() else null }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { refreshOwner!!.setRefreshState(true) }
            .doFinally { refreshOwner!!.setRefreshState(false) }
            .subscribe(
                { response ->
                    errorView.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    projectsAdapter!!.addData(response.projects, true)
                },
                { throwable ->
                    errorView.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                })
    }

    companion object {
        fun newInstance(): ProjectsFragment {
            return ProjectsFragment()
        }
    }
}