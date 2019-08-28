package app.mounika_news_project.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Articles : Serializable {

    @SerializedName("articles")
    var articles: List<News>? = null
}