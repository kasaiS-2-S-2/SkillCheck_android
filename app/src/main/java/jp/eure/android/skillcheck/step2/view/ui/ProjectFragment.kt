package jp.eure.android.skillcheck.step2.view.ui

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import jp.eure.android.skillcheck.R
import jp.eure.android.skillcheck.databinding.FragmentProjectDetailsBinding
import jp.eure.android.skillcheck.step2.service.model.Projects
import jp.eure.android.skillcheck.step2.viewModel.ProjectViewModel

class ProjectFragment : Fragment() {

    companion object {
        private const val OWNER_NAME = "owner_name"
        private const val NAME = "name"
        private const val STARGAZERS = "stargazers"
        private const val FORKS = "forks"
        private const val ISSUES = "issues"
        private const val WATCHERS = "watchers"
        private const val GIT_URL = "git_url"
        private const val ID = "id"
        private const val AVATAR_URL = "avatar_url"
        private const val LANGUAGE = "language"

        fun forProject(project: Projects.Item) = ProjectFragment().apply {
            arguments = Bundle().apply {
                putString(OWNER_NAME, project.owner.login)
                putString(NAME, project.name)
                putInt(STARGAZERS, project.stargazers_count)
                putInt(FORKS, project.forks_count)
                putInt(ISSUES, project.open_issues_count)
                putInt(WATCHERS, project.watchers_count)
                putString(GIT_URL, project.git_url)
                putInt(ID, project.id)
                putString(AVATAR_URL, project.owner.avatar_url)
                putString(LANGUAGE, project.language)
            }
        }
    }

    private val ownerName by lazy { requireNotNull(arguments?.getString(OWNER_NAME)) }
    private val name by lazy { requireNotNull(arguments?.getString(NAME)) }
    private val stargazers by lazy { requireNotNull(arguments?.getInt(STARGAZERS)) }
    private val forks by lazy { requireNotNull(arguments?.getInt(FORKS)) }
    private val issues by lazy { requireNotNull(arguments?.getInt(ISSUES)) }
    private val watchers by lazy { requireNotNull(arguments?.getInt(WATCHERS)) }
    private val git_url by lazy { requireNotNull(arguments?.getString(GIT_URL)) }
    private val project_id by lazy { requireNotNull(arguments?.getInt(ID)) }
    private val avatar_url by lazy { requireNotNull(arguments?.getString(AVATAR_URL)) }
    private val language by lazy { requireNotNull(arguments?.getString(LANGUAGE)) }

    private val viewModel by lazy {
        ViewModelProvider(this, ProjectViewModel.Factory(
                requireActivity().application, ownerName, name, stargazers, forks,
            issues, watchers, git_url, project_id, avatar_url, language
        )).get(ProjectViewModel::class.java)
    }

    private lateinit var binding: FragmentProjectDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            projectViewModel = viewModel
        }

        viewModel.projectLiveData.observe(viewLifecycleOwner, Observer { project ->
            project?.let {
                viewModel.setProject(it)
            }
        })
    }
}
