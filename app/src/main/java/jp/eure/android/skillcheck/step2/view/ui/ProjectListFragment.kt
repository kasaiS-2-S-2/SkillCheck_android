package jp.eure.android.skillcheck.step2.view.ui

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import jp.eure.android.skillcheck.R
import jp.eure.android.skillcheck.databinding.FragmentProjectListBinding
import jp.eure.android.skillcheck.step2.service.model.Projects
import jp.eure.android.skillcheck.step2.view.adapter.ProjectAdapter
import jp.eure.android.skillcheck.step2.view.callback.ProjectClickCallback
import jp.eure.android.skillcheck.step2.viewModel.ProjectListViewModel

const val TAG_OF_PROJECT_LIST_FRAGMENT = "ProjectListFragment"

class ProjectListFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(ProjectListViewModel::class.java)
    }

    private lateinit var binding: FragmentProjectListBinding
    private val projectAdapter: ProjectAdapter = ProjectAdapter(object : ProjectClickCallback {
        override fun onClick(project: Projects.Item) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                (activity as MainActivity).show(project)
            }
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)
        binding.apply {
            projectList.adapter = projectAdapter
        }
        binding.searchInputText
            .setOnEditorActionListener { editText, action, _ ->
                if (action == EditorInfo.IME_ACTION_SEARCH) {
                    editText.text.toString().let {
                        viewModel.loadProjectList(it)
                    }
                    hideKeyboard()
                    binding.apply {
                        isLoading = true
                    }
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.projectListLiveData.observe(viewLifecycleOwner, Observer { projects ->
            projects?.let {
                binding.isLoading = false
                projectAdapter.setProjectList(it)
            }
        })
    }

    private fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val manager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}
