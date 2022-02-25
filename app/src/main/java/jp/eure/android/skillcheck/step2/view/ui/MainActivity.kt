package jp.eure.android.skillcheck.step2.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import jp.eure.android.skillcheck.R
import jp.eure.android.skillcheck.step2.service.model.Projects

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            val fragment = ProjectListFragment() //一覧のFragment
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment, TAG_OF_PROJECT_LIST_FRAGMENT)
                    .commit()
        }
    }

    fun show(project: Projects.Item) {
        val projectFragment = ProjectFragment.forProject(project) //詳細のFragment
        supportFragmentManager
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container, projectFragment, null)
                .commit()
    }
}
