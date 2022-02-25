package jp.eure.android.skillcheck.step2.service.model

data class Projects(
    val items: List<Item>,
) {
    data class Item(
        val owner: Owner,
        var name: String,
        var stargazers_count: Int,
        var forks_count: Int,
        var open_issues_count: Int,
        var watchers_count: Int,
        var git_url: String,
        var id: Int,
        var language: String,
    ) {
        data class Owner(
            var login: String,
            var avatar_url: String,
        )
    }
}