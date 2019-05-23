package com.example.coursera_31_behancer_kotlin.ui.projects

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.coursera_31_behancer_kotlin.R
import com.example.coursera_31_behancer_kotlin.data.model.project.Project
import com.example.coursera_31_behancer_kotlin.databinding.ProjectBinding
import java.util.*

class ProjectsAdapter(private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<ProjectsHolder>() {

    private val projects = ArrayList<Project>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProjectBinding.inflate(inflater, parent, false)
        return ProjectsHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectsHolder, position: Int) {
        val project = projects[position]
        holder.bind(project, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    fun addData(data: List<Project>, isRefreshed: Boolean) {
        if (isRefreshed) {
            projects.clear()
        }

        // TODO: 09.04.2018 ДЗ обработать кейс с data.size == 0 || data == null

        projects.addAll(data)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(username: String)
    }
}