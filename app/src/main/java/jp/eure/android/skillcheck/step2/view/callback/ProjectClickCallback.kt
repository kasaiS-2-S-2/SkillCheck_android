package jp.eure.android.skillcheck.step2.view.callback

import jp.eure.android.skillcheck.step2.service.model.Projects

/**
 * @link onClick(Project project) 詳細画面に移動
 */
interface ProjectClickCallback {
    fun onClick(project: Projects.Item)
}
