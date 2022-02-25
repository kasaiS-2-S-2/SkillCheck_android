package jp.eure.android.skillcheck.step2.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import jp.eure.android.skillcheck.step2.service.model.Projects
import jp.eure.android.skillcheck.step2.service.repository.ProjectRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ProjectListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ProjectRepository.instance
    var projectListLiveData: MutableLiveData<List<Projects.Item>> = MutableLiveData()

    fun loadProjectList(query: String) = viewModelScope.launch {
        try {
            val response = repository.getProjectList(query)
            if (response.isSuccessful) {
                projectListLiveData.postValue(response.body()?.items)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
