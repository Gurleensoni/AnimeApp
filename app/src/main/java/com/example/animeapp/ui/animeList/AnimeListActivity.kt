package com.example.animeapp.ui.animeList

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animeapp.R
import com.example.animeapp.ui.animeDetail.AnimeDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnimeListActivity : AppCompatActivity() {

    private lateinit var adapter: AnimeAdapter
    private lateinit var recycler: RecyclerView
    private lateinit var searchInput: EditText
    private val viewModel: AnimeListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anime_list)

        // Views AFTER setContentView
        recycler = findViewById(R.id.animeRecyclerView)
        searchInput = findViewById(R.id.searchInput)

        adapter = AnimeAdapter { anime ->
            val intent = Intent(
                this,
                AnimeDetailActivity::class.java
            )
            intent.putExtra("anime_id", anime.id)
            startActivity(intent)
        }

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

        viewModel.animeList.observe(this) {
            adapter.submitList(it)
        }


        searchInput.addTextChangedListener {
            viewModel.searchAnime(it.toString())
        }

        recycler.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {

            override fun onScrolled(
                recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager =
                    recyclerView.layoutManager as LinearLayoutManager

                val lastVisible =
                    layoutManager.findLastVisibleItemPosition()

                val totalItems =
                    layoutManager.itemCount

                if (lastVisible >= totalItems - 5) {
                    viewModel.loadNextPage()
                }
            }
        })
    }
}
