package app.mounika_news_project.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import app.mounika_news_project.R
import app.mounika_news_project.Website
import app.mounika_news_project.entities.News
import app.mounika_news_project.util.Common
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val foContext: Context,
    private val mNewsList: List<News>
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onBindViewHolder(foViewHolder: ViewHolder, fiPosition: Int) {
        val news = mNewsList[fiPosition]
        foViewHolder.getNewsItem(news)

        if (news.imageUrl.isNullOrEmpty()) {
            foViewHolder.loTvImage.visibility = View.GONE
        } else {
            foViewHolder.loTvImage.visibility = View.VISIBLE
            Picasso.get().load(news.imageUrl).fit().into(foViewHolder.loTvImage)
        }

        if (news.description.isNullOrEmpty()) {
            foViewHolder.loTvDescription.visibility = View.GONE
        } else {
            foViewHolder.loTvDescription.visibility = View.VISIBLE
            foViewHolder.loTvDescription.text = news.description
        }

        foViewHolder.loTvTitle.text = news.title

        if (news.publishedDate.isNullOrEmpty().not()) {
            foViewHolder.loTvDate.text = Common.getRelativeTimeSpanString(news.publishedDate)
        }

        if (news.author.isNullOrEmpty().not()) {
            foViewHolder.loTvAuthor.text = news.author
        }
    }

    override fun onCreateViewHolder(foViewGroup: ViewGroup, fiPosition: Int): ViewHolder {
        val loView = LayoutInflater.from(foViewGroup.context).inflate(
            R.layout.item_news, foViewGroup, false
        )

        return ViewHolder(loView)
    }

    override fun getItemCount(): Int {
        return mNewsList.size
    }

    class ViewHolder(foView: View) : RecyclerView.ViewHolder(foView),
        View.OnClickListener {

        private lateinit var loNews: News

        var loTvImage: ImageView
        var loTvTitle: TextView
        var loTvAuthor: TextView
        var loTvDescription: TextView
        var loTvDate: TextView
        var loBtnRead: Button

        init {
            foView.setOnClickListener(this)
            loTvImage = foView.findViewById(R.id.ivImage)
            loTvTitle = foView.findViewById(R.id.tvTitle)
            loTvAuthor = foView.findViewById(R.id.tvAuthor)
            loTvDescription = foView.findViewById(R.id.tvDescription)
            loTvDate = foView.findViewById(R.id.tvPublished)
            loBtnRead = foView.findViewById(R.id.btnRead)
            loBtnRead.setOnClickListener(this)
        }

        fun getNewsItem(foNews: News) {
            loNews = foNews
        }

        override fun onClick(view: View) {
            val intent = Intent(view.context, Website::class.java)
            intent.putExtra("NewsItem", loNews)
            view.context.startActivity(intent)
        }
    }
}