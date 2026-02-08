package com.example.animeapp.ui.animeList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.animeapp.R
import com.example.animeapp.data.local.entity.AnimeEntity

class AnimeAdapter(
    private val onClick: (AnimeEntity) -> Unit
) : ListAdapter<AnimeEntity, AnimeAdapter.AnimeViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_anime, parent, false)
        return AnimeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AnimeViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        private val title =
            view.findViewById<TextView>(R.id.title)

        private val rating =
            view.findViewById<TextView>(R.id.rating)

        private val episodes =
            view.findViewById<TextView>(R.id.episodes)

        private val poster =
            view.findViewById<ImageView>(R.id.poster)

        fun bind(anime: AnimeEntity) {

            itemView.setOnClickListener {
                onClick(anime)
            }

            title.text = anime.title

            rating.text =
                "Rating: ${anime.score ?: "N/A"}"

            episodes.text =
                "Episodes: ${anime.episodes ?: "N/A"}"

            Glide.with(itemView)
                .load(anime.imageUrl)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(poster)
        }
    }

    companion object {
        val DIFF = object : DiffUtil.ItemCallback<AnimeEntity>() {
            override fun areItemsTheSame(old: AnimeEntity, new: AnimeEntity)
                    = old.id == new.id

            override fun areContentsTheSame(old: AnimeEntity, new: AnimeEntity)
                    = old == new
        }
    }
}
