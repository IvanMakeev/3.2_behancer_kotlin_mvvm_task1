package com.example.coursera_31_behancer_kotlin.ui.projects

import android.support.v7.widget.RecyclerView
import com.example.coursera_31_behancer_kotlin.data.model.project.Project
import com.example.coursera_31_behancer_kotlin.databinding.ProjectBinding

class ProjectsHolder(private val projectBinding: ProjectBinding) : RecyclerView.ViewHolder(projectBinding.root) {

    fun bind(item: Project, onItemClickListener: ProjectsAdapter.OnItemClickListener?) {

        projectBinding.project = ProjectListItemViewModel(item)
        projectBinding.onItemClickListener = onItemClickListener
        projectBinding.executePendingBindings()

//        Picasso.with(image.context).load(item.cover!!.photoUrl)
//            .fit()
//            .into(image)
//
//        name.text = item.name
//        username.text = item.owners!![FIRST_OWNER_INDEX].username
//        publishedOn.text = DateUtils.format(item.publishedOn)
//
//        if (onItemClickListener != null) {
//            itemView.setOnClickListener {
//                onItemClickListener.onItemClick(
//                    item.owners!![FIRST_OWNER_INDEX]
//                        .username
//                )
//            }
//        }
    }
}