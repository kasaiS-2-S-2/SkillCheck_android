package jp.eure.android.skillcheck.step2.service.repository

import jp.eure.android.skillcheck.step2.service.model.Projects
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("/search/repositories")
    suspend fun getProjectList(@Query("q") query: String) : Response<Projects>
}
