package app.mounika_news_project

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.mounika_news_project.adapter.NewsAdapter
import app.mounika_news_project.entities.Articles
import app.mounika_news_project.entities.News
import app.mounika_news_project.util.ApiClient
import app.mounika_news_project.util.ApiInterface
import app.mounika_news_project.util.Common
import app.mounika_news_project.util.Constant.Companion.API_KEY
import app.mounika_news_project.util.Constant.Companion.CATEGORY
import app.mounika_news_project.util.Constant.Companion.COUNTRY_CODE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsList : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_list)

        setupTitle(getString(R.string.TITLE))

        if (Common.isInternetConnected(this)) {
            getNewsList()
        } else {
            Common.showErrorDialog(this, getString(R.string.CONNECTION_ERROR))
        }
    }

    /**
     * API call to retrieve news list and populate with RecyclerView on success
     */
    private fun getNewsList() {
        showProgressDialog()

        val apiInterface = ApiClient.getClient()?.create(ApiInterface::class.java)

        val call = apiInterface?.getNews(COUNTRY_CODE, CATEGORY, API_KEY)
        call?.enqueue(object : Callback<Articles> {
            override fun onResponse(call: Call<Articles>, response: Response<Articles>) {
                hideProgressDialog()
                val news = response.body()
                if (news != null) {
                    Log.i("TAG", "List: " + news.articles)
                    news.articles?.let { setupData(it) }
                } else {
                    Common.showErrorDialog(this@NewsList, getString(R.string.NO_DATA))
                }
            }

            override fun onFailure(call: Call<Articles>, t: Throwable) {
                hideProgressDialog()
                Log.i("TAG", "Error: $t")
                Common.showErrorDialog(this@NewsList, getString(R.string.SERVER_ERROR))
            }
        })
    }

    /**
     * @param fNewsList News list retrieved from API
     */
    fun setupData(fNewsList: List<News>) {
        val rvNews = findViewById<RecyclerView>(R.id.rvNewsList)
        val manager = LinearLayoutManager(this)
        manager.orientation = RecyclerView.VERTICAL
        rvNews.layoutManager = manager
        rvNews.adapter = NewsAdapter(this, fNewsList)
    }
}
