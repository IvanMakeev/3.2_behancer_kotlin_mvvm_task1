package com.example.coursera_31_behancer_kotlin.ui.projects

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.coursera_31_behancer_kotlin.R
import com.example.coursera_31_behancer_kotlin.data.model.project.Project
import com.example.coursera_31_behancer_kotlin.utils.DateUtils
import com.squareup.picasso.Picasso

class ProjectsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val image: ImageView = itemView.findViewById(R.id.image)
    private val name: TextView = itemView.findViewById(R.id.tv_name)
    private val username: TextView = itemView.findViewById(R.id.tv_username)
    private val publishedOn: TextView = itemView.findViewById(R.id.tv_published)

    fun bind(item: Project, onItemClickListener: ProjectsAdapter.OnItemClickListener?) {
        Picasso.with(image.context).load(item.cover!!.photoUrl)
            .fit()
            .into(image)

        name.text = item.name
        username.text = item.owners!![FIRST_OWNER_INDEX].username
        publishedOn.text = DateUtils.format(item.publishedOn)

        if (onItemClickListener != null) {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(
                    item.owners!![FIRST_OWNER_INDEX]
                        .username
                )
            }
        }
    }

    companion object {
        private const val FIRST_OWNER_INDEX = 0
    }
}