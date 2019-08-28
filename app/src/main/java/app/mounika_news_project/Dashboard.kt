package app.mounika_news_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        val btnGetNewsList = findViewById<Button>(R.id.btnNewsList)
        btnGetNewsList.setOnClickListener {
            val intent = Intent(this, NewsList::class.java)
            startActivity(intent)
        }
    }
}