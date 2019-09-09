package com.example.coursera_31_behancer_kotlin.ui.projects

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coursera_31_behancer_kotlin.R
import com.example.coursera_31_behancer_kotlin.data.Storage
import com.example.coursera_31_behancer_kotlin.databinding.ProjectsBinding

abstract class BaseProjectsFragment : Fragment() {

    companion object {
        const val LIST_STATE_KEY = "LIST_STATE_KEY"
    }

    protected var baseProjectsViewModel: BaseProjectsViewModel? = null
    protected lateinit var storage: Storage
    protected lateinit var recyclerView: RecyclerView
    protected var listState: Parcelable? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is Storage.StorageOwner) {
            storage = context.obtainStorage()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = ProjectsBinding.inflate(inflater, container, false)
        binding.vm = baseProjectsViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recycler)
    }

    override fun onResume() {
        super.onResume()
        listState?.let {
            recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        listState = recyclerView.layoutManager?.onSaveInstanceState()
        outState.putParcelable(LIST_STATE_KEY, listState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        savedInstanceState?.let {
            listState = it.getParcelable(LIST_STATE_KEY)
        }
    }
}