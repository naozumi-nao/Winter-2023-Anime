package com.dicoding.winter2023anime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.winter2023anime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<Anime>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvAnime.setHasFixedSize(true)

        list.addAll(getListAnime())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about_page -> {
                showAbout()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListAnime(): ArrayList<Anime> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataSynopsis = resources.getStringArray(R.array.data_synopsis)
        val dataCover = resources.getStringArray(R.array.data_cover)
        val dataRelease = resources.getStringArray(R.array.data_release)
        val dataEngTitle = resources.getStringArray(R.array.data_eng_title)
        val dataEpisodes = resources.getIntArray(R.array.data_episodes)
        val dataRating = resources.getStringArray(R.array.data_rating)
        val listAnime = ArrayList<Anime>()
        for (i in dataTitle.indices) {
            val anime = Anime(dataTitle[i], dataSynopsis[i], dataCover[i], dataRelease[i], dataEngTitle[i], dataEpisodes[i], dataRating[i])
            listAnime.add(anime)
        }
        return listAnime
    }

    private fun showRecyclerList() {
        binding.rvAnime.layoutManager = LinearLayoutManager(this)
        val listAnimeAdapter = ListAnimeAdapter(list)
        binding.rvAnime.adapter = listAnimeAdapter

        listAnimeAdapter.setOnItemClickCallback((object : ListAnimeAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Anime) {
                showSelectedAnime(data)
            }
        }))
    }

    private fun showSelectedAnime(anime: Anime) {
        val moveWithDataIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithDataIntent.apply {
            putExtra(DetailActivity.EXTRA_TITLE, anime.title)
            putExtra(DetailActivity.EXTRA_SYNOPSIS, anime.synopsis)
            putExtra(DetailActivity.EXTRA_COVER, anime.cover)
            putExtra(DetailActivity.EXTRA_RELEASE, anime.release)
            putExtra(DetailActivity.EXTRA_ENG_TITLE, anime.engTitle)
            putExtra("episodes", anime.episodes)
            putExtra(DetailActivity.EXTRA_RATING, anime.rating)
        }
        startActivity(moveWithDataIntent)
    }

    private fun showAbout() {
        val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
        startActivity(moveIntent)
    }

}