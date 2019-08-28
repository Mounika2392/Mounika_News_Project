package app.mounika_news_project.util

import app.mounika_news_project.entities.Articles
import app.mounika_news_project.entities.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    /**
     * @param country country code (eg: us)
     * @param category news category (eg: entertainment)
     * @param apiKey key retrieved from https://newsapi.org/ for API call (a0ad596d351546bbb90ddf3b2041a776)
     * @return list of news from API
     */
    @GET(".")
    fun getNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey: String
    ): Call<Articles>
}