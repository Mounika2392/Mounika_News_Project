package app.mounika_news_project.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class News : Serializable {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("author")
    var author: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("urlToImage")
    var imageUrl: String? = null

    @SerializedName("publishedAt")
    var publishedDate: String? = null
}