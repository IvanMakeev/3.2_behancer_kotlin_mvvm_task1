package com.example.coursera_31_behancer_kotlin.ui.projects

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.coursera_31_behancer_kotlin.data.model.project.RichProject
import com.example.coursera_31_behancer_kotlin.databinding.ProjectBinding

class ProjectsAdapter(
    private val onItemClickListener: OnItemClickListener
) :
    PagedListAdapter<RichProject, ProjectsHolder>(CALLBACK) {

    companion object {
        @JvmStatic
        val CALLBACK = object : DiffUtil.ItemCallback<RichProject>() {
            override fun areItemsTheSame(oldItem: RichProject, newItem: RichProject): Boolean {
                return oldItem.project!!.id == newItem.project!!.id
            }

            override fun areContentsTheSame(oldItem: RichProject, newItem: RichProject): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProjectBinding.inflate(inflater, parent, false)
        return ProjectsHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectsHolder, position: Int) {
        val project = getItem(position)

        if (project != null) {
            holder.bind(project, onItemClickListener)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(username: String)
    }
}