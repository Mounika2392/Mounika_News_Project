package app.mounika_news_project.util

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private val BASE_URL = "https://newsapi.org/v2/top-headlines/"
    private var retrofit: Retrofit? = null


    /**
     * Setup Retrofit API client
     * @return retrofit object to make API call
     */
    fun getClient(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}