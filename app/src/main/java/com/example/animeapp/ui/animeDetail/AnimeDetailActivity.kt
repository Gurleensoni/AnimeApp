package com.example.animeapp.ui.animeDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.animeapp.R
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels

@AndroidEntryPoint
class AnimeDetailActivity : AppCompatActivity(){

private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: TextView
    private lateinit var synopsis: TextView
    private lateinit var genres: TextView
    private lateinit var episodes: TextView
    private lateinit var trailerBtn: TextView
    private lateinit var cast: TextView
private val viewModel: AnimeDetailViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_detail)

        val animeId = intent.getIntExtra("anime_id", -1)
        if (animeId == -1) {
            finish()
            return
        }

        // Views AFTER setContentView
        poster = findViewById(R.id.poster)
        title = findViewById(R.id.title)
        rating = findViewById(R.id.rating)
        synopsis = findViewById(R.id.synopsis)
        genres = findViewById(R.id.genres)
        episodes = findViewById(R.id.episodes)
        trailerBtn = findViewById(R.id.trailerBtn)
        cast = findViewById(R.id.cast)
        viewModel.cast.observe(this) { castText ->
            cast.text = "Main Cast: $castText"
        }




        viewModel.loadAnimeDetail(animeId)
            .observe(this) { anime ->

            anime ?: return@observe

                title.text = anime.title ?: "Unknown"
                rating.text = "Rating: ${anime.score ?: "N/A"}"
                synopsis.text = anime.synopsis ?: "No synopsis"
                genres.text = "Genres: ${anime.genres ?: "N/A"}"
                episodes.text = "Episodes: ${anime.episodes ?: "?"}"


                Glide.with(this)
                .load(anime.imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(poster)


                trailerBtn.setOnClickListener {
                    anime.trailerUrl?.let { url ->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent)
                        }
                    }
                }

            }
    }
}
