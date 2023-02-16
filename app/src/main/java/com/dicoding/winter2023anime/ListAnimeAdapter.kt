package com.dicoding.winter2023anime

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.winter2023anime.databinding.ItemRowAnimeBinding

class ListAnimeAdapter(private val listAnime: ArrayList<Anime>) : RecyclerView.Adapter<ListAnimeAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(var binding: ItemRowAnimeBinding) : RecyclerView.ViewHolder(binding.root)

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowAnimeBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (title, synopsis, cover) = listAnime[position]

        Glide.with(holder.itemView.context)
            .load(cover)
            .error(R.drawable.fumo)
            .into(holder.binding.imgItemCover)
        holder.apply {
            binding.tvItemTitle.text = title
            binding.tvItemSynopsis.text = synopsis
            itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(listAnime[holder.adapterPosition])
            }
        }

    }

    override fun getItemCount(): Int = listAnime.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Anime)
    }
}