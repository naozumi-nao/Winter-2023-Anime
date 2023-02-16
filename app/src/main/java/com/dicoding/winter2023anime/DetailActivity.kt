package com.dicoding.winter2023anime

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.winter2023anime.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TITLE = "animeh"
        const val EXTRA_SYNOPSIS = "MC overpowered"
        const val EXTRA_COVER = "link animeh"
        const val EXTRA_RELEASE = "tanggal rilis"
        const val EXTRA_ENG_TITLE = "animation"
        const val EXTRA_RATING = "11 out of 10"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra(EXTRA_TITLE)
        val synopsis = intent.getStringExtra(EXTRA_SYNOPSIS)
        val cover = intent.getStringExtra(EXTRA_COVER)
        val release = intent.getStringExtra(EXTRA_RELEASE)
        val engTitle = intent.getStringExtra(EXTRA_ENG_TITLE)
        val episodes = intent.getIntExtra("episodes", 0)
        val rating = intent.getStringExtra(EXTRA_RATING)

        this.supportActionBar?.title = title

        binding.apply {
            tvAnimeTitle.text = title
            tvAnimeSynopsis.text = synopsis
            Glide.with(applicationContext)
                .load(cover)
                .placeholder(R.drawable.fumo)
                .error(R.drawable.fumo)
                .into(ivAnimeCover)
            tvAnimeRelease.text = release
            tvAnimeEngTitle.text = engTitle
            tvAnimeEpisodes.text = episodes.toString().trim()
            tvAnimeRating.text = rating
            rbAnimeRating.rating = rating!!.toFloat()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_share -> {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Hai, aku mau menonton $title lho!, apakah kamu juga akan menontonnya?"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
