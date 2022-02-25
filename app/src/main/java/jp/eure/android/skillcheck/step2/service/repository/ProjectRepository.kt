package jp.eure.android.skillcheck.step2.service.repository

import jp.eure.android.skillcheck.step2.service.model.Projects
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val HTTPS_API_GITHUB_URL = "https://api.github.com/"

class ProjectRepository {
    companion object Factory {
        val instance: ProjectRepository
            @Synchronized get() {
                return ProjectRepository()
            }
    }

    private val retrofit = Retrofit.Builder()
            .baseUrl(HTTPS_API_GITHUB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val githubService: GithubService = retrofit.create(GithubService::class.java)

    suspend fun getProjectList(query: String): Response<Projects> =
        githubService.getProjectList(query)
}
