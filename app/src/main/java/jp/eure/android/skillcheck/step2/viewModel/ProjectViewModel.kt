package jp.eure.android.skillcheck.step2.viewModel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.*

import jp.eure.android.skillcheck.step2.service.model.Projects

class ProjectViewModel(
        private val myApplication: Application,
        private val mOwnerName: String,
        private val mName: String,
        private val mStargazers: Int,
        private val mForks: Int,
        private val mIssues: Int,
        private val mWatchers: Int,
        private val mGitUrl: String,
        private val mProjectId: Int,
        private val mAvatarUrl: String,
        private val mLanguage: String,

) : AndroidViewModel(myApplication) {

    val projectLiveData: MutableLiveData<Projects.Item> = MutableLiveData()

    var project = ObservableField<Projects.Item>()

    init {
        initProject()
    }

    private fun initProject() {
        val owner = Projects.Item.Owner(mOwnerName, mAvatarUrl)
        val project = Projects.Item(owner, mName, mStargazers, mForks, mIssues,
            mWatchers, mGitUrl, mProjectId, mLanguage)
        projectLiveData.postValue(project)
    }


    fun setProject(project: Projects.Item) {
        this.project.set(project)
    }

    class Factory(private val application: Application, private val ownerName: String,
                  private val name: String, private val stargazers: Int,
                  private val forks: Int, private val issues: Int,
                  private val watchers: Int, private val git_url: String,
                  private val project_id: Int, private val avatar_url: String,
                  private  val language: String) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ProjectViewModel(application, ownerName, name, stargazers, forks, issues,
                watchers, git_url, project_id, avatar_url, language) as T
        }
    }
}
