package jp.eure.android.skillcheck.step2.view.adapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import jp.eure.android.skillcheck.R
import jp.eure.android.skillcheck.databinding.ProjectListItemBinding
import jp.eure.android.skillcheck.step2.view.callback.ProjectClickCallback
import androidx.recyclerview.widget.ListAdapter
import jp.eure.android.skillcheck.step2.service.model.Projects

class ProjectAdapter(
    private val projectClickCallback: ProjectClickCallback?,
) : ListAdapter<Projects.Item, ProjectAdapter.ProjectViewHolder>(diff_util) {

private var projectList: List<Projects.Item>? = null
    open class ProjectViewHolder(val binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun setProjectList(projectList: List<Projects.Item>) {

        if (this.projectList == null) {
            this.projectList = projectList
            notifyItemRangeInserted(0, projectList.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return requireNotNull(this@ProjectAdapter.projectList).size
                }

                override fun getNewListSize(): Int {
                    return projectList.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldList = this@ProjectAdapter.projectList
                    return oldList?.get(oldItemPosition)?.id == projectList[newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val project = projectList[newItemPosition]
                    val old = projectList[oldItemPosition]
                    return project.id == old.id && project.git_url == old.git_url
                }
            })
            this.projectList = projectList
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): ProjectViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.project_list_item, parent,
            false) as ProjectListItemBinding
        binding.callback = projectClickCallback
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.project = projectList?.get(position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return projectList?.size ?: 0
    }

}

val diff_util = object : DiffUtil.ItemCallback<Projects.Item>() {

    override fun areItemsTheSame(oldItem: Projects.Item, newItem: Projects.Item): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Projects.Item, newItem: Projects.Item): Boolean {
        return oldItem == newItem
    }
}
