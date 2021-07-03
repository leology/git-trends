package com.leology.gittrends.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.leology.gittrends.R
import com.leology.gittrends.value_objects.RepoListItem
import kotlinx.android.synthetic.main.recycler_view_list_row.view.*

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listItem: List<RepoListItem>? = null

    fun setUpdatedItem(listItem: List<RepoListItem>) {
        this.listItem = listItem
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val profileIcon = view.image_view          // Directly invoking the ID value of a View is possible due to the Kotlin extensions
        val repoName = view.text_view_name
        val repoDescription = view.text_view_description

        fun bind(data: RepoListItem) {
            repoName.text = data.author // for a dataclass, use the 'data' followed by the val name.
            repoDescription.text = data.description

            Glide.with(profileIcon)
                .load(data.avatar)
                .apply(RequestOptions.centerCropTransform())
                .into(profileIcon)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listItem?.get(position)!!)
    }

    override fun getItemCount(): Int {
        return if(listItem==null) 0;
        else listItem?.size!!
    }
}